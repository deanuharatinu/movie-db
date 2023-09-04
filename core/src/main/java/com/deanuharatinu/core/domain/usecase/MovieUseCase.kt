package com.deanuharatinu.core.domain.usecase

import com.deanuharatinu.core.domain.Resource
import com.deanuharatinu.core.domain.model.FavoriteMovie
import com.deanuharatinu.core.domain.model.MovieDetail
import com.deanuharatinu.core.domain.model.PopularMovie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
  fun getPopularMovies(): Flow<Resource<List<PopularMovie>>>
  fun getFavoriteMovies(): Flow<Resource<List<FavoriteMovie>>>
  fun getMovieDetail(movieId: String): Flow<Resource<MovieDetail>>

  suspend fun addFavoriteMovie(favoriteMovie: FavoriteMovie)
}