package com.deanuharatinu.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deanuharatinu.core.data.source.local.model.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insertFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity)

  @Query("SELECT * FROM favorite_movie")
  fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>>
}