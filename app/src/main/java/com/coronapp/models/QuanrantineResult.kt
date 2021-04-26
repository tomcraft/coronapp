package com.coronapp.models


import com.google.gson.annotations.SerializedName

data class QuanrantineResult<T>(
    @SerializedName("data")
    val `data`: T,
    @SerializedName("status")
    val status: Int,
    @SerializedName("type")
    val type: String
)