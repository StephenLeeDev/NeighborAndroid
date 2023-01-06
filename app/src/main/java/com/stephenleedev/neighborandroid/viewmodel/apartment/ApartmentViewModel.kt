package com.stephenleedev.neighborandroid.viewmodel.apartment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stephenleedev.neighborandroid.domain.model.apartment.ApartmentState
import com.stephenleedev.neighborandroid.domain.usecase.apartment.GetAllApartmentListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Written by StephenLeeDev on 2022/12/23.
 */

@HiltViewModel
class ApartmentViewModel @Inject constructor(
    private val getAllApartmentListUseCase: GetAllApartmentListUseCase
) : ViewModel() {

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

}