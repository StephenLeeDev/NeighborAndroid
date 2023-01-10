package com.stephenleedev.neighborandroid.viewmodel.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stephenleedev.neighborandroid.ui.main.NavigationTabItemType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Written by StephenLeeDev on 2023/01/09.
 */

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel() {

    private val _navigationTabState = MutableLiveData(NavigationTabItemType.NAVIGATION_MAP.type)
    val navigationTabState = _navigationTabState as LiveData<String>

    fun setNavigationTabState(tab: String) {
        _navigationTabState.value = tab
    }

}