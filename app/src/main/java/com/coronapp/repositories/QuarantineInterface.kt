package com.coronapp.repositories

import com.coronapp.models.QuanrantineResult
import com.coronapp.models.Region
import com.coronapp.models.RegionSummary
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QuarantineInterface {

    @GET("v1/regions")
    fun getRegions() : Call<QuanrantineResult<List<Region>>>

    @GET("v1/summary/region")
    fun getSummary(@Query("region")regionKey: String) : Call<QuanrantineResult<RegionSummary>>

}