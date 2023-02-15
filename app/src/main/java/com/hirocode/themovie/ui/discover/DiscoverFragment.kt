package com.hirocode.themovie.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.hirocode.themovie.core.ui.DiscoverAdapter
import com.hirocode.themovie.core.ui.LoadingStateAdapter
import com.hirocode.themovie.R
import com.hirocode.themovie.databinding.FragmentDiscoverBinding
import com.hirocode.themovie.ui.genres.GenresViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscoverFragment : Fragment() {

    private val discoverViewModel: DiscoverViewModel by viewModel()
    private val genresViewModel: GenresViewModel by viewModel()
    private var _binding: FragmentDiscoverBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        genresViewModel.genres.observe(viewLifecycleOwner) { genres ->
            if (genres != null) {
                val arrayString = ArrayList<String>()
                val items = genres.data?.map { it.name }
                if (items != null) {
                    for (item in items) {
                        arrayString.add(item)
                    }
                }
                val adapter =
                    ArrayAdapter(requireContext(), R.layout.item_name, arrayString.toTypedArray())
                binding?.acGenres?.setAdapter(adapter)
                binding?.acGenres?.onItemClickListener =
                    AdapterView.OnItemClickListener { _, _, position, _ ->
                        val genreId = genres.data?.map { it.id }?.get(position)
                        getDiscover(genreId)
                    }
            }
        }
    }

    private fun getDiscover(genreId: Int?) {
        binding?.rvDiscover?.layoutManager = LinearLayoutManager(context)
        val adapter = DiscoverAdapter()
        binding?.rvDiscover?.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        adapter.addLoadStateListener { loadState ->
            binding?.apply {
                progressBar.isVisible = loadState.refresh is LoadState.Loading
                rvDiscover.isVisible = loadState.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.refresh is LoadState.Error
                textViewError.isVisible = loadState.refresh is LoadState.Error

                if (loadState.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && adapter.itemCount < 1) {
                    rvDiscover.isVisible = false
                    textViewEmpty.isVisible = true
                } else {
                    textViewEmpty.isVisible = false
                }
            }
        }
        binding?.buttonRetry?.setOnClickListener {
            adapter.retry()
        }
        discoverViewModel.getGenreId(genreId)
        discoverViewModel.discover.observe(viewLifecycleOwner) { discover ->
            adapter.submitData(lifecycle, discover)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}