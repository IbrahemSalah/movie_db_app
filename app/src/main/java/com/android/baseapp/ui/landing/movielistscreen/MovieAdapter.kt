package com.android.baseapp.ui.landing.movielistscreen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.baseapp.R
import com.android.baseapp.data.model.Movie
import com.android.baseapp.databinding.MovieItemBinding
import com.android.baseapp.util.Constants
import com.bumptech.glide.Glide

class MovieAdapter(val context: Context, var dataSource: List<Movie?>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var onItemClick: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataSource[position]

        currentItem?.let {
            holder.bind(currentItem)
            holder.itemView.setOnClickListener { onItemClick?.invoke(currentItem) }
        }
    }


    override fun getItemCount() = dataSource.size

    fun swapData(dataSource: List<Movie?>) {
        this.dataSource = dataSource
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: MovieItemBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {


            binding.tvMovieName.text = item.title
            binding.tvMovieReleaseDate.text = item.releaseDate
            binding.tvMovieDesc.text = item.overview

            Glide.with(context)
                .load(Constants.POSTER_PREFIX_URL + item.backdropPath)
                .error(R.drawable.ic_baseline_error_24)
                .into(binding.ivMoviePoster)
        }
    }
}