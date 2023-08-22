package com.deanuharatinu.moviedatabase.core.data.source.remote.model

import com.deanuharatinu.moviedatabase.core.domain.model.PopularMovie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularMovieResponse(
  @Json(name = "page")
  val page: Int? = null,
  @Json(name = "total_pages")
  val totalPages: Int? = null,
  @Json(name = "results")
  val results: List<PopularMovieItemResponse?>? = null,
  @Json(name = "total_results")
  val totalResults: Int? = null
)

@JsonClass(generateAdapter = true)
data class PopularMovieItemResponse(
  @Json(name = "overview")
  val overview: String? = null,
  @Json(name = "original_language")
  val originalLanguage: String? = null,
  @Json(name = "original_title")
  val originalTitle: String? = null,
  @Json(name = "video")
  val video: Boolean? = null,
  @Json(name = "title")
  val title: String? = null,
  @Json(name = "genre_ids")
  val genreIds: List<Int?>? = null,
  @Json(name = "poster_path")
  val posterPath: String? = null,
  @Json(name = "backdrop_path")
  val backdropPath: String? = null,
  @Json(name = "release_date")
  val releaseDate: String? = null,
  @Json(name = "popularity")
  val popularity: Any? = null,
  @Json(name = "vote_average")
  val voteAverage: Float? = null,
  @Json(name = "id")
  val id: Int? = null,
  @Json(name = "adult")
  val adult: Boolean? = null,
  @Json(name = "vote_count")
  val voteCount: Int? = null
) {
  companion object {
    fun toDomain(item: PopularMovieItemResponse): PopularMovie {
      return PopularMovie(
        title = item.title.orEmpty(),
        photoUrl = item.posterPath.orEmpty(),
        voteAverage = item.voteAverage ?: 0.0F,
      )
    }
  }
}
