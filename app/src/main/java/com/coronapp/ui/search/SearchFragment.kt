package com.coronapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.coronapp.R
import com.coronapp.adapters.RegionListAdapter
import com.coronapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        val binding: FragmentSearchBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_search, container, false
        )

        searchViewModel.regionsList.observe(viewLifecycleOwner, { regions ->
            val regionListAdapter = RegionListAdapter(regions) {
                val action = SearchFragmentDirections.actionNavigationSearchToDetailsFragment(
                    regionId = it.key,
                    regionName = it.name,
                    regionCode = it.iso3166a2
                )
                findNavController().navigate(action)
            }
            binding.recyclerViewRegion.adapter = regionListAdapter
        })

        searchViewModel.loadRegions()

        return binding.root
    }

}
