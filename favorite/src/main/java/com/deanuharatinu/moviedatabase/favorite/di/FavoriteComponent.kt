package com.deanuharatinu.moviedatabase.favorite.di

import android.content.Context
import com.deanuharatinu.moviedatabase.favorite.FavoriteFragment
import com.deanuharatinu.moviedatabase.ui.di.FavoriteModule
import dagger.BindsInstance
import dagger.Component

@Component(
  dependencies = [FavoriteModule::class],
)
interface FavoriteComponent {
  fun inject(fragment: FavoriteFragment)

  @Component.Builder
  interface Builder {
    fun context(@BindsInstance context: Context): Builder
    fun appDependencies(favoriteModule: FavoriteModule): Builder
    fun build(): FavoriteComponent
  }
}