package com.deanuharatinu.moviedatabase.core.domain.repository

import com.deanuharatinu.moviedatabase.core.domain.model.FavoriteMovie
import com.deanuharatinu.moviedatabase.core.domain.model.PopularMovie
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface IMovieRepository {
  fun getPopularMovies(): Flow<List<PopularMovie>>
  fun getFavoriteMovies(): Flow<List<FavoriteMovie>>
}