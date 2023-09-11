package com.deanuharatinu.core.data.source.remote.model

import androidx.annotation.Keep
import com.deanuharatinu.core.domain.model.MovieDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class MovieDetailResponse(
  @Json(name = "original_language")
  val originalLanguage: String? = null,
  @Json(name = "imdb_id")
  val imdbId: String? = null,
  @Json(name = "video")
  val video: Boolean? = null,
  @Json(name = "title")
  val title: String? = null,
  @Json(name = "genres")
  val genres: List<GenresItemResponse?>? = null,
  @Json(name = "id")
  val id: Int? = null,
  @Json(name = "overview")
  val overview: String? = null,
  @Json(name = "runtime")
  val runtime: Int? = null,
  @Json(name = "poster_path")
  val posterPath: String? = null,
  @Json(name = "vote_average")
  val voteAverage: Float? = null,
) {
  companion object {
    fun toDomain(item: MovieDetailResponse): MovieDetail {
      return MovieDetail(
        movieId = item.id ?: 0,
        title = item.title.orEmpty(),
        runTime = item.runtime ?: 0,
        posterUrl = item.posterPath.orEmpty(),
        rating = item.voteAverage ?: 0.0F,
        synopsis = item.overview.orEmpty(),
        genres = item.genres?.map { genreResponse ->
          genreResponse?.name.orEmpty()
        }.orEmpty(),
      )
    }
  }
}

@JsonClass(generateAdapter = true)
data class GenresItemResponse(
  @Json(name = "name")
  val name: String? = null
)