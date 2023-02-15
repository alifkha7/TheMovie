package com.hirocode.themovie.ui.genres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hirocode.themovie.R
import com.hirocode.themovie.core.data.Resource
import com.hirocode.themovie.core.ui.GenresAdapter
import com.hirocode.themovie.databinding.FragmentGenresBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenresFragment : Fragment() {

    private val genresViewModel: GenresViewModel by viewModel()
    private var _binding: FragmentGenresBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGenresBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val genresAdapter = GenresAdapter()
            genresViewModel.genres.observe(viewLifecycleOwner) { genres ->
                if (genres != null) {
                    when(genres) {
                        is Resource.Loading -> binding?.progressBar?.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding?.progressBar?.visibility = View.GONE
                            genresAdapter.setData(genres.data)
                        }
                        is Resource.Error -> {
                            binding?.progressBar?.visibility = View.GONE
                            binding?.viewError?.root?.visibility = View.VISIBLE
                            binding?.viewError?.tvError?.text = genres.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }
            with(binding?.rvGenres) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = genresAdapter
                this?.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}