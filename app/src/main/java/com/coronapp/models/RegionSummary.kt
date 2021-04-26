package com.coronapp.models


import com.google.gson.annotations.SerializedName

data class RegionSummary(
    @SerializedName("change")
    val change: RegionSummaryValues,
    @SerializedName("summary")
    val summary: RegionSummaryValues
)