package com.deanuharatinu.moviedatabase.core.domain.usecase

import com.deanuharatinu.moviedatabase.core.domain.model.FavoriteMovie
import com.deanuharatinu.moviedatabase.core.domain.model.PopularMovie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
  fun getPopularMovies(): Flow<List<PopularMovie>>
  fun getFavoriteMovies(): Flow<List<FavoriteMovie>>
}