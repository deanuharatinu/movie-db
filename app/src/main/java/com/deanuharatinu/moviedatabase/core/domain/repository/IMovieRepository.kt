package com.deanuharatinu.moviedatabase.core.domain.repository

import com.deanuharatinu.moviedatabase.core.domain.Resource
import com.deanuharatinu.moviedatabase.core.domain.model.FavoriteMovie
import com.deanuharatinu.moviedatabase.core.domain.model.MovieDetail
import com.deanuharatinu.moviedatabase.core.domain.model.PopularMovie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
  fun getPopularMovies(): Flow<Resource<List<PopularMovie>>>
  fun getFavoriteMovies(): Flow<List<FavoriteMovie>>

  fun getMovieDetail(movieId: String): Flow<Resource<MovieDetail>>
}