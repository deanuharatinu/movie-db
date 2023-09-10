package com.deanuharatinu.moviedatabase.ui.di

import com.deanuharatinu.core.domain.usecase.MovieInteractor
import com.deanuharatinu.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
  @Binds
  abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase
}