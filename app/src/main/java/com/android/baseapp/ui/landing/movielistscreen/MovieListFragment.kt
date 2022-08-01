package com.android.baseapp.ui.landing.movielistscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.android.baseapp.R
import com.android.baseapp.databinding.FragmentMovieListBinding
import com.android.baseapp.ui.base.BaseViewModelFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MovieListFragment : BaseViewModelFragment<MovieListViewModel, FragmentMovieListBinding>(
    MovieListViewModel::class) {



    private val movieAdapter by lazy {
        MovieAdapter(requireContext(), arrayListOf())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initViews() {
        super.initViews()

        binding.rvMovie.adapter = movieAdapter
        movieAdapter.onItemClick = {
            findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(it))
        }
    }

    override fun initObservers() {
        super.initObservers()

        viewModel.movieListResponseState.onEach {
            when (it) {
                is MovieListResponseState.Failure -> {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                is MovieListResponseState.Loading -> {
                    if (it.loading)
                        showDialog(getString(R.string.loading))
                    else
                        dismissDialog()
                }
                is MovieListResponseState.Success -> {
                    it.response.results?.let { movieList ->
                        movieAdapter.swapData(movieList)
                    }
                }
            }
        }.launchIn(lifecycleScope)
    }
}