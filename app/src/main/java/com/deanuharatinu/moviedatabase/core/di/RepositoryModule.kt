package com.deanuharatinu.moviedatabase.core.di

import com.deanuharatinu.moviedatabase.core.data.repository.MovieRepository
import com.deanuharatinu.moviedatabase.core.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
  @Binds
  abstract fun provideMovieRepository(movieRepository: MovieRepository): IMovieRepository
}