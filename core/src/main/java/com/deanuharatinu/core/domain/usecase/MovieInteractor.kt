package com.deanuharatinu.core.domain.usecase

import com.deanuharatinu.core.domain.Resource
import com.deanuharatinu.core.domain.model.FavoriteMovie
import com.deanuharatinu.core.domain.model.MovieDetail
import com.deanuharatinu.core.domain.model.PopularMovie
import com.deanuharatinu.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(
  private val movieRepository: IMovieRepository
) : MovieUseCase {
  override fun getPopularMovies(): Flow<Resource<List<PopularMovie>>> =
    movieRepository.getPopularMovies()

  override fun getMovieDetail(movieId: String): Flow<Resource<MovieDetail>> =
    movieRepository.getMovieDetail(movieId)

  override fun getFavoriteMovies(): Flow<List<FavoriteMovie>> = movieRepository.getFavoriteMovies()
}