package com.deanuharatinu.core.di

import android.content.Context
import androidx.room.Room
import com.deanuharatinu.core.BuildConfig
import com.deanuharatinu.core.data.source.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

  @Provides
  fun provideDatabase(
    @ApplicationContext context: Context,
  ): AppDatabase {
    val passphrase: ByteArray = SQLiteDatabase.getBytes(BuildConfig.CHIPER_KEY.toCharArray())
    val factory = SupportFactory(passphrase)
    return Room.databaseBuilder(
      context,
      AppDatabase::class.java,
      "app_database"
    ).openHelperFactory(factory).build()
  }
}