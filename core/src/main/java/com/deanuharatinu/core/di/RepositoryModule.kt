package com.deanuharatinu.core.di

import com.deanuharatinu.core.data.repository.MovieRepository
import com.deanuharatinu.core.domain.repository.IMovieRepository
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