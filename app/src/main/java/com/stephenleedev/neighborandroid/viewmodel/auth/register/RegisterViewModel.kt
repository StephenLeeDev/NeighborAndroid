package com.stephenleedev.neighborandroid.viewmodel.auth.register

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stephenleedev.neighborandroid.domain.model.apartment.ApartmentState
import com.stephenleedev.neighborandroid.domain.model.auth.purpose.SignUpPurposeModel
import com.stephenleedev.neighborandroid.domain.model.auth.register.RegisterRequest
import com.stephenleedev.neighborandroid.domain.model.auth.register.RegisterState
import com.stephenleedev.neighborandroid.domain.model.user.thumbnail.UserThumbnailUpdateState
import com.stephenleedev.neighborandroid.domain.usecase.apartment.GetAllApartmentListUseCase
import com.stephenleedev.neighborandroid.domain.usecase.auth.PostAuthRegisterUseCase
import com.stephenleedev.neighborandroid.domain.usecase.user.PatchUserThumbnailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Written by StephenLeeDev on 2022/12/28.
 */

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val getAllApartmentListUseCase: GetAllApartmentListUseCase,
    private val postAuthRegisterUseCase: PostAuthRegisterUseCase,
    private val patchUserThumbnailUseCase: PatchUserThumbnailUseCase
) : ViewModel() {

    /**
     * Current page of ViewPager
     * Range == 1~3
     */
    private val _currentPage = MutableLiveData(0)
    val currentPage = _currentPage as LiveData<Int>

    fun setCurrentPage(page: Int) {
        _currentPage.value = page
    }

    /**
     * List of all apartments
     */
    private val _apartmentState = MutableLiveData<ApartmentState>(ApartmentState.Loading)
    val apartmentState = _apartmentState as LiveData<ApartmentState>

    fun setApartmentState(value: ApartmentState) {
        _apartmentState.value = value
    }

    fun getAllApartmentList() {
        viewModelScope.launch {
            getAllApartmentListUseCase.execute()
                .catch {
                    setApartmentState(ApartmentState.Fail)
                }
                .collect { list ->
                    setApartmentState(ApartmentState.Success(list))
                }
        }
    }

    /**
     * List of all apartments
     */
    private val _selectedApartmentId = MutableLiveData<Int>(-1)
    val selectedApartmentId = _selectedApartmentId as LiveData<Int>

    fun setSelectedApartmentId(value: Int) {
        _selectedApartmentId.value = value
    }

    /**
     * Nickname of user
     */
    private val _nickname = MutableLiveData("")
    val nickname = _nickname as LiveData<String>

    fun setNickname(name: String) {
        _nickname.value = name
    }

    /**
     * Selected sign-up purpose's ID list
     */
    private val _selectedPurposeIdList = MutableLiveData<List<Int>>()
    val selectedPurposeIdList = _selectedPurposeIdList as LiveData<List<Int>>

    private fun setSelectedPurposeIdList(value: List<Int>) {
        _selectedPurposeIdList.value = value
    }

    // Initialize selected purpose ID list state by new purpose state
    fun setSelectedPurposeIdListByNewState(list: List<SignUpPurposeModel>) {

        val selectedList = list.filter { it.isSelected }

        val tempList: ArrayList<Int> = arrayListOf()

        selectedList.forEach { selectedItem ->
            if (selectedItem.isSelected) {
                tempList.add(selectedItem.id)
            }
        }

        setSelectedPurposeIdList(tempList.toList())
    }

    /**
     * Validate that all forms have been completed so that move on to the next
     */
    private val _isValid = MutableLiveData(false)
    val isValid = _isValid as LiveData<Boolean>

    private fun setIsValid(bool: Boolean) {
        _isValid.value = bool
    }

    fun checkIsValid() {

        val page = currentPage.value ?: -1
        val apartmentId = selectedApartmentId.value ?: -1

        when (page) {
            1 -> {
                setIsValid(true)
            }
            2 -> {
                setIsValid(apartmentId > 0)
            }
            3 -> {
                val name = nickname.value ?: ""
                val purposes = selectedPurposeIdList.value ?: emptyList()

                setIsValid(name.isNotEmpty() && purposes.isNotEmpty())
            }
        }

    }

    /**
     * Post Auth Register State
     */
    private val _registerState = MutableLiveData<RegisterState>(RegisterState.Loading)
    val registerState = _registerState as LiveData<RegisterState>

    private fun setRegisterState(value: RegisterState) {
        _registerState.value = value
    }

    fun postAuthRegister(socialType: String, socialToken: String) {
        val apartmentId = selectedApartmentId.value ?: return
        val name = nickname.value ?: return
        val purposes = selectedPurposeIdList.value ?: return

        viewModelScope.launch {
            postAuthRegisterUseCase.execute(
                body = RegisterRequest(
                    socialType = socialType,
                    socialToken = socialToken,
                    apartmentId = apartmentId,
                    nickname = name,
                    purposeIds = purposes
                )
            )
                .catch { setRegisterState(RegisterState.Fail) }
                .collect { response ->
                    setRegisterState(RegisterState.Success(response))
                }
        }
    }

}