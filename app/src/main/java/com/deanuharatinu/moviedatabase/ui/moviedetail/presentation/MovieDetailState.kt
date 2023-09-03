package com.deanuharatinu.moviedatabase.ui.moviedetail.presentation

import com.deanuharatinu.core.domain.model.MovieDetail
import kotlin.time.Duration.Companion.minutes

data class MovieDetailUi(
  val title: String,
  val runTime: String,
  val posterUrl: String,
  val rating: Float,
  val synopsis: String,
  val genres: List<String>,
) {
  companion object {
    fun fromDomain(movieDetail: com.deanuharatinu.core.domain.model.MovieDetail): MovieDetailUi {
      return MovieDetailUi(
        title = movieDetail.title,
        runTime = movieDetail.runTime.getReadableRuntime(),
        posterUrl = movieDetail.posterUrl.getImageUrl(),
        rating = movieDetail.rating,
        synopsis = movieDetail.synopsis,
        genres = movieDetail.genres,
      )
    }

    private fun String.getImageUrl(): String {
      return "https://image.tmdb.org/t/p/w500/${this}"
    }

    private fun Int.getReadableRuntime(): String {
      val duration = this.minutes
      return String.format("%d hours %02d minutes", duration.inWholeHours, duration.inWholeMinutes)
    }
  }
}