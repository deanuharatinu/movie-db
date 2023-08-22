package com.deanuharatinu.moviedatabase.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deanuharatinu.moviedatabase.core.domain.usecase.MovieInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val movieUseCase: MovieInteractor,
) : ViewModel() {

  fun getData() {
    viewModelScope.launch {
      movieUseCase.getPopularMovies().collect { popularMovies ->
        popularMovies.map { println(it.title) }
      }
    }
  }
}