package com.deanuharatinu.moviedatabase.ui.home.presentation

import com.deanuharatinu.core.domain.model.PopularMovie

data class PopularMovieUi(
  val movieId: String,
  val title: String,
  val photoUrl: String,
  val voteAverage: Float,
) {
  companion object {
    fun fromDomain(popularMovie: com.deanuharatinu.core.domain.model.PopularMovie): PopularMovieUi {
      return PopularMovieUi(
        movieId = popularMovie.movieId,
        title = popularMovie.title,
        photoUrl = popularMovie.photoUrl.getImageUrl(),
        voteAverage = popularMovie.voteAverage
      )
    }

    private fun String.getImageUrl(): String {
      return "https://image.tmdb.org/t/p/w300${this}"
    }
  }
}