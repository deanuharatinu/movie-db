package com.deanuharatinu.core.domain.usecase

import com.deanuharatinu.core.di.IoDispatcher
import com.deanuharatinu.core.domain.Resource
import com.deanuharatinu.core.domain.model.FavoriteMovie
import com.deanuharatinu.core.domain.model.MovieDetail
import com.deanuharatinu.core.domain.model.PopularMovie
import com.deanuharatinu.core.domain.repository.IMovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieInteractor @Inject constructor(
  private val movieRepository: IMovieRepository,
  @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : MovieUseCase {
  override fun getPopularMovies(): Flow<Resource<List<PopularMovie>>> =
    movieRepository.getPopularMovies()

  @OptIn(ExperimentalCoroutinesApi::class)
  override fun getMovieDetail(movieId: String): Flow<Resource<MovieDetail>> = flow {
    movieRepository.getMovieDetail(movieId).flatMapMerge { remote ->
      flow {
        val local = movieRepository.getFavoriteMovieById(movieId).first()
        if (remote is Resource.Success && local is Resource.Success) {
          val remoteMovieDetail = remote.data
          remoteMovieDetail.isFavorite = true
          emit(Resource.Success(remoteMovieDetail))
        } else {
          emit(remote)
        }
      }
    }.flowOn(ioDispatcher)
      .collect { emit(it) }
  }

  override fun getFavoriteMovies(): Flow<Resource<List<FavoriteMovie>>> =
    movieRepository.getFavoriteMovies()

  override suspend fun addFavoriteMovie(favoriteMovie: FavoriteMovie) {
    movieRepository.addFavoriteMovie(favoriteMovie)
  }
}