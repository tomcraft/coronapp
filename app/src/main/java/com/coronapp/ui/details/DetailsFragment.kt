package com.coronapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.coronapp.R
import com.coronapp.databinding.FragmentDetailsBinding
import com.coronapp.models.QuanrantineResult
import com.coronapp.models.Region
import com.coronapp.models.RegionSummary
import com.coronapp.repositories.retrofitClient
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class DetailsFragment : Fragment() {

    private lateinit var detailsViewModel: DetailsViewModel
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        val binding: FragmentDetailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details, container, false
        )

        binding.viewModel = detailsViewModel

        binding.regionName.text = args.regionName

        val code = args.regionCode.toLowerCase(Locale.ROOT);
        Picasso.get().load("https://raw.githubusercontent.com/hampusborgos/country-flags/main/png250px/$code.png").into(binding.regionFlag)

        retrofitClient.getSummary(args.regionId).enqueue(object :
            Callback<QuanrantineResult<RegionSummary>> {
            override fun onFailure(call: Call<QuanrantineResult<RegionSummary>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<QuanrantineResult<RegionSummary>>, response: Response<QuanrantineResult<RegionSummary>>) {
                val region = response.body()?.data

                binding.totalCases.text = binding.totalCases.text.toString().replace("{value}", "${region?.summary?.totalCases}")
                binding.activeCases.text = binding.activeCases.text.toString().replace("{value}", "${region?.summary?.activeCases}")
                binding.deaths.text = binding.deaths.text.toString().replace("{value}", "${region?.summary?.deaths}")
                binding.recovered.text = binding.recovered.text.toString().replace("{value}", "${region?.summary?.recovered}")
                binding.criticalCases.text = binding.criticalCases.text.toString().replace("{value}", "${region?.summary?.critical}")
                binding.testAmount.text = binding.testAmount.text.toString().replace("{value}", "${region?.summary?.tested}")
            }
        })

        return binding.root
    }
}