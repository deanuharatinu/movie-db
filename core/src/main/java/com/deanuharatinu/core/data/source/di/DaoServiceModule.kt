package com.deanuharatinu.core.data.source.di

import com.deanuharatinu.core.data.source.local.AppDatabase
import com.deanuharatinu.core.data.source.local.dao.FavoriteMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoServiceModule {
  @Provides
  fun provideFavoriteMovieDao(appDatabase: AppDatabase): FavoriteMovieDao {
    return appDatabase.favoriteMovieDao()
  }
}