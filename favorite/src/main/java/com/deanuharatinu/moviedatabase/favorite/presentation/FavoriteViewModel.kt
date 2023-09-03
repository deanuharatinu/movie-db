package com.deanuharatinu.moviedatabase.favorite.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deanuharatinu.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
  private val movieUseCase: MovieUseCase
) : ViewModel() {
  init {
    viewModelScope.launch {
      movieUseCase.getPopularMovies().collect {
        Log.d("TAG", ": ")
      }
    }
  }
}