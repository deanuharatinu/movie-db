package com.deanuharatinu.moviedatabase.ui.moviedetail.presentation

import com.deanuharatinu.core.domain.model.MovieDetail
import kotlin.time.Duration.Companion.minutes

data class MovieDetailUi(
  val movieId: Int,
  val title: String,
  val runTime: Int,
  val posterUrl: String,
  val rating: Float,
  val synopsis: String,
  val genres: List<String>,
) {
  companion object {
    fun fromDomain(movieDetail: MovieDetail): MovieDetailUi {
      return MovieDetailUi(
        movieId = movieDetail.movieId,
        title = movieDetail.title,
        runTime = movieDetail.runTime,
        posterUrl = movieDetail.posterUrl,
        rating = movieDetail.rating,
        synopsis = movieDetail.synopsis,
        genres = movieDetail.genres,
      )
    }

    fun String.getImageUrl(): String {
      return "https://image.tmdb.org/t/p/w500/${this}"
    }

    fun Int.getReadableRuntime(): String {
      val duration = this.minutes
      return String.format("%d hours %02d minutes", duration.inWholeHours, duration.inWholeMinutes)
    }
  }
}