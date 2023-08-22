package com.deanuharatinu.moviedatabase.core.data.source.di

import com.deanuharatinu.moviedatabase.core.data.source.remote.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {
  @Provides
  fun provideMovieService(retrofit: Retrofit): MovieService {
    return retrofit.create(MovieService::class.java)
  }
}