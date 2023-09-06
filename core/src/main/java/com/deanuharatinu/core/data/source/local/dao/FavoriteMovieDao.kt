package com.deanuharatinu.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deanuharatinu.core.data.source.local.model.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class FavoriteMovieDao {
  suspend fun addFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity) {
    val favoriteMovie = getFavoriteMovieById(favoriteMovieEntity.movieId.toString())
    if (favoriteMovie == null) {
      insertFavoriteMovie(favoriteMovieEntity)
    } else {
      deleteFavoriteMovie(favoriteMovieEntity)
    }
  }

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract suspend fun insertFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity)

  @Delete
  abstract suspend fun deleteFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity)

  @Query("SELECT * FROM favorite_movie")
  abstract fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>>

  @Query("SELECT * FROM favorite_movie WHERE movieId = :movieId")
  abstract fun getFavoriteMovieById(movieId: String): FavoriteMovieEntity?
}