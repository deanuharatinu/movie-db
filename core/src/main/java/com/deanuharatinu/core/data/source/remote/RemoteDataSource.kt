package com.deanuharatinu.core.data.source.remote

import android.util.Log
import com.deanuharatinu.core.BuildConfig
import com.deanuharatinu.core.data.source.remote.model.MovieDetailResponse
import com.deanuharatinu.core.data.source.remote.model.PopularMovieItemResponse
import com.deanuharatinu.core.di.IoDispatcher
import com.deanuharatinu.core.domain.model.MovieDetail
import com.deanuharatinu.core.domain.model.PopularMovie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
  private val movieService: MovieService,
  @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {
  fun getPopularMovie(): Flow<ApiResponse<List<PopularMovie>>> = flow {
    try {
      val response = movieService.getPopularMovie(BuildConfig.API_KEY)
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
      Log.e(TAG, "getPopularMovie: ", throwable)
      emit(ApiResponse.Error(throwable.message.toString()))
    }
  }.flowOn(ioDispatcher)

  fun getMovieDetail(movieId: String): Flow<ApiResponse<MovieDetail>> = flow {
    try {
      val response = movieService.getMovieDetail(movieId, BuildConfig.API_KEY)
      val movieDetail = MovieDetailResponse.toDomain(response)
      emit(ApiResponse.Success(movieDetail))
    } catch (throwable: Throwable) {
      Log.e(TAG, "getMovieDetail: ", throwable)
      emit(ApiResponse.Error(throwable.message.toString()))
    }
  }.flowOn(ioDispatcher)

  companion object {
    const val TAG = "RemoteDataSource"
  }
}