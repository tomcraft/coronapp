package com.coronapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coronapp.models.QuanrantineResult
import com.coronapp.models.Region
import com.coronapp.repositories.retrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    val regionsList = MutableLiveData<List<Region>>()

    fun loadRegions() {
        retrofitClient.getRegions().enqueue(object : Callback<QuanrantineResult<List<Region>>> {
            override fun onFailure(call: Call<QuanrantineResult<List<Region>>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<QuanrantineResult<List<Region>>>, response: Response<QuanrantineResult<List<Region>>>) {
                val sorted = response.body()?.data!!.sortedBy { it.name }
                regionsList.value = sorted
            }
        })
    }

}