package com.deanuharatinu.core.di

import android.content.Context
import androidx.room.Room
import com.deanuharatinu.core.data.source.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

  @Provides
  fun provideDatabase(
    @ApplicationContext context: Context,
  ): AppDatabase {
    return Room.databaseBuilder(
      context,
      AppDatabase::class.java,
      "app_database"
    ).build()
  }
}