package com.coronapp.models


import com.google.gson.annotations.SerializedName

data class Region(
    @SerializedName("iso3166a2")
    val iso3166a2: String,
    @SerializedName("iso3166a3")
    val iso3166a3: String,
    @SerializedName("iso3166numeric")
    val iso3166numeric: Any,
    @SerializedName("key")
    val key: String,
    @SerializedName("name")
    val name: String
)