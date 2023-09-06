package com.deanuharatinu.core.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.deanuharatinu.core.domain.model.FavoriteMovie

@Entity(tableName = "favorite_movie")
data class FavoriteMovieEntity(
  @PrimaryKey
  val movieId: Int?,
  val title: String?,
  val photoUrl: String?,
  val voteAverage: Float?,
  val description: String?,
) {
  companion object {
    fun toDomain(favoriteMovieEntity: FavoriteMovieEntity): FavoriteMovie {
      return FavoriteMovie(
        movieId = favoriteMovieEntity.movieId ?: 0,
        title = favoriteMovieEntity.title.orEmpty(),
        photoUrl = favoriteMovieEntity.photoUrl.orEmpty(),
        voteAverage = favoriteMovieEntity.voteAverage ?: 0.0F,
        description = favoriteMovieEntity.description.orEmpty(),
      )
    }

    fun fromDomain(favoriteMovie: FavoriteMovie): FavoriteMovieEntity {
      return FavoriteMovieEntity(
        movieId = favoriteMovie.movieId,
        title = favoriteMovie.title,
        photoUrl = favoriteMovie.photoUrl,
        voteAverage = favoriteMovie.voteAverage,
        description = favoriteMovie.description,
      )
    }
  }
}
