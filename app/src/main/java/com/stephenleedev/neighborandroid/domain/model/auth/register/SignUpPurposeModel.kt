package com.stephenleedev.neighborandroid.domain.model.auth.register

import com.google.gson.annotations.SerializedName

/**
 * Written by StephenLeeDev on 2023/01/01.
 */

data class SignUpPurposeModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
) {
    var isSelected: Boolean = false
}