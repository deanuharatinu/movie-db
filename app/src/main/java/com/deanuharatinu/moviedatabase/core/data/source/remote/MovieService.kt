package com.deanuharatinu.moviedatabase.core.data.source.remote

import com.deanuharatinu.moviedatabase.core.data.source.remote.model.PopularMovieResponse
import com.deanuharatinu.moviedatabase.core.utils.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
  @GET(ApiConstants.MOVIE_POPULAR)
  suspend fun getPopularMovie(@Query("api_key") apiKey: String): PopularMovieResponse
}
