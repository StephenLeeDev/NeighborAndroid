package com.stephenleedev.neighborandroid.domain.model.apartment


import com.google.gson.annotations.SerializedName
import com.stephenleedev.neighborandroid.domain.model.common.LocationModel

data class ApartmentModel(
    @SerializedName("address")
    val address: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("location")
    val location: LocationModel,
    @SerializedName("name")
    val name: String,

    var isSelected: Boolean = false
)