package com.deanuharatinu.moviedatabase.favorite.presentation

import com.deanuharatinu.core.domain.model.FavoriteMovie

data class FavoriteMovieUi(
  val movieId: Int,
  val title: String,
  val photoUrl: String,
  val voteAverage: Float,
) {
  companion object {
    fun fromDomain(favoriteMovie: FavoriteMovie): FavoriteMovieUi {
      return FavoriteMovieUi(
        movieId = favoriteMovie.movieId,
        title = favoriteMovie.title,
        photoUrl = favoriteMovie.photoUrl.getImageUrl(),
        voteAverage = favoriteMovie.voteAverage
      )
    }

    private fun String.getImageUrl(): String {
      return "https://image.tmdb.org/t/p/w300${this}"
    }
  }
}