package com.deanuharatinu.moviedatabase.core.data.repository

import com.deanuharatinu.moviedatabase.core.data.source.remote.ApiResponse
import com.deanuharatinu.moviedatabase.core.data.source.remote.RemoteDataSource
import com.deanuharatinu.moviedatabase.core.domain.Resource
import com.deanuharatinu.moviedatabase.core.domain.model.FavoriteMovie
import com.deanuharatinu.moviedatabase.core.domain.model.MovieDetail
import com.deanuharatinu.moviedatabase.core.domain.model.PopularMovie
import com.deanuharatinu.moviedatabase.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Collections
import javax.inject.Inject

class MovieRepository @Inject constructor(
  private val remoteDataSource: RemoteDataSource,
//  private val localDataSource: LocalDataSource,
) : IMovieRepository {
  override fun getPopularMovies(): Flow<Resource<List<PopularMovie>>> = flow {
    remoteDataSource.getPopularMovie().collect { result ->
      when (result) {
        is ApiResponse.Success -> {
          emit(Resource.Success(result.data))
        }

        is ApiResponse.Empty -> {
          emit(Resource.Success(Collections.emptyList()))
        }

        is ApiResponse.Error -> {
          emit(Resource.Error(result.errorMessage))
        }
      }
    }
  }

  override fun getFavoriteMovies(): Flow<List<FavoriteMovie>> {
    TODO("Not yet implemented")
  }

  override fun getMovieDetail(movieId: String): Flow<Resource<MovieDetail>> = flow {
    remoteDataSource.getMovieDetail(movieId).collect { result ->
      when (result) {
        is ApiResponse.Success -> {
          emit(Resource.Success(result.data))
        }

        is ApiResponse.Empty -> {
          emit(Resource.Error("no data"))
        }

        is ApiResponse.Error -> {
          emit(Resource.Error(result.errorMessage))
        }
      }
    }
  }
}