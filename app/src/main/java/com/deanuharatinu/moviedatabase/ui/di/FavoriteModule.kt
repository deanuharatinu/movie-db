package com.deanuharatinu.moviedatabase.ui.di

import com.deanuharatinu.core.domain.usecase.MovieUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModule {
  fun provideMovieUseCase(): MovieUseCase
}
