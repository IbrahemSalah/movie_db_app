package com.android.baseapp.ui.landing.moviedetailscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.android.baseapp.R
import com.android.baseapp.data.model.MovieDetail
import com.android.baseapp.databinding.FragmentMovieDetailBinding
import com.android.baseapp.ui.base.BaseViewModelFragment
import com.android.baseapp.util.Constants
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MovieDetailFragment : BaseViewModelFragment<MovieDetailViewModel, FragmentMovieDetailBinding>(
    MovieDetailViewModel::class
) {

    private val navArg: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initViews() {
        super.initViews()
        viewModel.getMovieDetail(navArg.movie.id.toString())
    }

    override fun initObservers() {
        super.initObservers()

        viewModel.movieDetailResponseState.onEach {
            when (it) {
                is MovieDetailResponseState.Failure -> {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                is MovieDetailResponseState.Loading -> {
                    if (it.loading)
                        showDialog(getString(R.string.loading))
                    else
                        dismissDialog()
                }
                is MovieDetailResponseState.Success -> {
                    it.response?.let { movieDetail ->
                        showMovieDetails(movieDetail)
                    }
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun showMovieDetails(movieDetail: MovieDetail) {
        binding.tvMovieName.text = movieDetail.title
        binding.tvMovieCategory.text = movieDetail.genres?.get(0)?.name
        binding.tvMovieUrl.text = movieDetail.homepage
        binding.tvMovieDesc.text = movieDetail.overview

        Glide.with(this)
            .load(Constants.POSTER_PREFIX_URL + movieDetail.backdrop_path)
            .error(R.drawable.ic_baseline_error_24)
            .into(binding.ivMoviePoster);
    }
}