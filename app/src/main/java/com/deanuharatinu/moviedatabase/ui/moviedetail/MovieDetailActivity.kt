package com.deanuharatinu.moviedatabase.ui.moviedetail

import android.content.res.ColorStateList
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
import com.deanuharatinu.core.utils.IntentConstants
import com.deanuharatinu.moviedatabase.R
import com.deanuharatinu.moviedatabase.databinding.ActivityMovieDetailBinding
import com.deanuharatinu.moviedatabase.ui.moviedetail.presentation.MovieDetailUi
import com.deanuharatinu.moviedatabase.ui.moviedetail.presentation.MovieDetailUi.Companion.getImageUrl
import com.deanuharatinu.moviedatabase.ui.moviedetail.presentation.MovieDetailUi.Companion.getReadableRuntime
import com.deanuharatinu.moviedatabase.ui.moviedetail.presentation.MovieDetailViewModel
import com.deanuharatinu.moviedatabase.ui.moviedetail.presentation.ViewModelState
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMovieDetailBinding
  private val viewModel: MovieDetailViewModel by viewModels()
  private lateinit var movieDetailUi: MovieDetailUi

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMovieDetailBinding.inflate(layoutInflater)
    setContentView(binding.root)

    initData()
    initOnClick()
  }

  private fun initData() {
    val movieId = intent.getStringExtra(IntentConstants.MOVIE_ID)
    viewModel.getMovieDetail(movieId.orEmpty())

    lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.uiState.collect(::loadContent)
      }
    }
  }

  private fun initOnClick() {
    binding.btnNavBack.setOnClickListener { finish() }

    binding.addFavorite.setOnClickListener {
      viewModel.addFavoriteMovie(movieDetailUi)
    }
  }

  private fun loadContent(viewModelState: ViewModelState) {
    val circularProgressDrawable = CircularProgressDrawable(this)
    circularProgressDrawable.strokeWidth = 10f
    circularProgressDrawable.centerRadius = 60f
    circularProgressDrawable.start()

    if (!viewModelState.isLoading) {
      viewModelState.movieDetail?.let { movieDetailUi ->
        this.movieDetailUi = movieDetailUi

        with(binding) {
          Glide
            .with(root.context)
            .load(movieDetailUi.posterUrl.getImageUrl())
            .placeholder(circularProgressDrawable)
            .into(ivMoviePoster)

          tvRuntime.text = movieDetailUi.runTime.getReadableRuntime()
          tvMovieTitle.text = movieDetailUi.title
          tvRating.apply {
            visibility = View.VISIBLE
            text = String.format("%.2f", movieDetailUi.rating)
          }
          tvSynopsis.text = movieDetailUi.synopsis

          addFavorite.imageTintList = setFavoriteButtonColor(movieDetailUi.isFavorite)

          cgGenres.chipGroup.removeAllViews()
          movieDetailUi.genres.forEach { genre ->
            val chip = LayoutInflater.from(this@MovieDetailActivity)
              .inflate(R.layout.item_chip_genre, root, false)
            (chip as Chip).text = genre

            cgGenres.chipGroup.addView(chip)
          }
        }
      }
    }
  }

  private fun setFavoriteButtonColor(isFavorite: Boolean): ColorStateList =
    if (isFavorite) {
      ColorStateList.valueOf(getColor(R.color.orange_100))
    } else {
      ColorStateList.valueOf(getColor(R.color.white_100))
    }
}