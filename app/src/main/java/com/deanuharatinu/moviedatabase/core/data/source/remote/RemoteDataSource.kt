package com.deanuharatinu.moviedatabase.core.data.source.remote

import android.util.Log
import com.deanuharatinu.moviedatabase.core.data.source.remote.model.PopularMovieItemResponse
import com.deanuharatinu.moviedatabase.core.domain.model.PopularMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
  private val movieService: MovieService
) {
  fun getPopularMovie(): Flow<ApiResponse<List<PopularMovie>>> = flow {
    try {
      val response = movieService.getPopularMovie("9779cbcba5d3823d303e404aa822c5bc")
      val data = response.results
      if (!data.isNullOrEmpty()) {
        val popularMovies = data.mapNotNull { itemResponse ->
          itemResponse?.let { PopularMovieItemResponse.toDomain(itemResponse) }
        }
        emit(ApiResponse.Success(popularMovies))
      } else {
        emit(ApiResponse.Empty)
      }
    } catch (throwable: Throwable) {
      Log.e("asdf", "getPopularMovie: ", throwable)
      emit(ApiResponse.Error(throwable.message.toString()))
    }
  }.flowOn(Dispatchers.IO)
}