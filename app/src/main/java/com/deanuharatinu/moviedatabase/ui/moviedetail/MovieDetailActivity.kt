package com.deanuharatinu.moviedatabase.ui.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.deanuharatinu.moviedatabase.R
import com.deanuharatinu.moviedatabase.databinding.ActivityMovieDetailBinding
import com.deanuharatinu.moviedatabase.ui.moviedetail.presentation.MovieDetailViewModel
import com.deanuharatinu.moviedatabase.ui.moviedetail.presentation.ViewModelState
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMovieDetailBinding
  private val viewModel: MovieDetailViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMovieDetailBinding.inflate(layoutInflater)
    setContentView(binding.root)

    initData()
    initOnClick()
  }

  private fun initData() {
    val movieId = intent.getStringExtra("movie_id")
    viewModel.getMovieDetail(movieId.orEmpty())

    lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.uiState.collect(::loadContent)
      }
    }
  }

  private fun initOnClick() {
    binding.btnNavBack.setOnClickListener { finish() }
  }

  private fun loadContent(viewModelState: ViewModelState) {
    val circularProgressDrawable = CircularProgressDrawable(this)
    circularProgressDrawable.strokeWidth = 10f
    circularProgressDrawable.centerRadius = 60f
    circularProgressDrawable.start()

    if (viewModelState.isLoading) {
      startShimmer()
    } else {
      viewModelState.movieDetail?.let { movieDetailUi ->
        stopShimmer()

        Glide
          .with(binding.root.context)
          .load(movieDetailUi.posterUrl)
          .placeholder(circularProgressDrawable)
          .into(binding.ivMoviePoster)

        binding.tvRuntime.text = movieDetailUi.runTime
        binding.tvMovieTitle.text = movieDetailUi.title
        binding.tvRating.apply {
          visibility = View.VISIBLE
          text = movieDetailUi.rating.toString()
        }
        binding.tvSynopsis.text = movieDetailUi.synopsis

        binding.cgGenres.chipGroup.removeAllViews()
        movieDetailUi.genres.forEach { genre ->
          val chip = LayoutInflater.from(this)
            .inflate(R.layout.item_chip_genre, binding.root, false)
          (chip as Chip).text = genre

          binding.cgGenres.chipGroup.addView(chip)
        }
      }
    }
  }

  private fun startShimmer() {

  }

  private fun stopShimmer() {

  }
}