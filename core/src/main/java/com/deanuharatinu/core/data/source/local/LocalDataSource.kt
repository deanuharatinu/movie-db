package com.deanuharatinu.core.data.source.local

import com.deanuharatinu.core.data.source.local.dao.FavoriteMovieDao
import com.deanuharatinu.core.data.source.local.model.FavoriteMovieEntity
import com.deanuharatinu.core.domain.model.FavoriteMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
  private val movieDao: FavoriteMovieDao
) {
  fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>> = movieDao.getFavoriteMovies()

  suspend fun addFavoriteMovie(favoriteMovie: FavoriteMovie) {
    movieDao.insertFavoriteMovie(FavoriteMovieEntity.fromDomain(favoriteMovie))
  }
}