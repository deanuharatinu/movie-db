package com.deanuharatinu.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.deanuharatinu.core.data.source.local.dao.FavoriteMovieDao
import com.deanuharatinu.core.data.source.local.model.FavoriteMovieEntity

@Database(entities = [FavoriteMovieEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun favoriteMovieDao(): FavoriteMovieDao
}