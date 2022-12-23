package com.stephenleedev.neighborandroid.domain.model.apartment

/**
 * Written by StephenLeeDev on 2022/12/23.
 */

sealed class ApartmentState {
    object Loading : ApartmentState()
    object Fail : ApartmentState()
    data class Success(val list: List<ApartmentModel>) : ApartmentState()
}
