package com.deanuharatinu.moviedatabase.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.deanuharatinu.moviedatabase.favorite.databinding.FragmentFavoriteBinding
import com.deanuharatinu.moviedatabase.favorite.di.DaggerFavoriteComponent
import com.deanuharatinu.moviedatabase.favorite.presentation.FavoriteViewModel
import com.deanuharatinu.moviedatabase.favorite.presentation.ViewModelState
import com.deanuharatinu.moviedatabase.ui.di.FavoriteModule
import com.deanuharatinu.moviedatabase.ui.moviedetail.MovieDetailActivity
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteFragment : Fragment() {
  private var _binding: FragmentFavoriteBinding? = null
  private val binding get() = _binding!!
  private lateinit var adapter: FavoriteMovieAdapter

  @Inject
  lateinit var viewModel: FavoriteViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    DaggerFavoriteComponent.builder()
      .context(requireContext())
      .appDependencies(
        EntryPointAccessors.fromApplication(
          requireContext(),
          FavoriteModule::class.java
        )
      )
      .build()
      .inject(this)

    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initRecyclerView()
    initDataObserver()
  }

  override fun onResume() {
    super.onResume()
    viewModel.getFavoriteMovies()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun initRecyclerView() {
    adapter = FavoriteMovieAdapter { movieId ->
      val intent = Intent(requireActivity(), MovieDetailActivity::class.java)
      intent.putExtra("movie_id", movieId)
      startActivity(intent)
    }
    binding.rvFavoriteMovie.adapter = adapter
  }

  private fun initDataObserver() {
    lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.RESUMED) {
        viewModel.uiState.collect(::loadContent)
      }
    }
  }

  private fun loadContent(viewModelState: ViewModelState) {
    if (viewModelState.favoriteMovie.isEmpty()) {
      binding.layoutNotFound.visibility = View.VISIBLE
    } else {
      binding.layoutNotFound.visibility = View.GONE
      adapter.submitList(viewModelState.favoriteMovie)
    }
  }
}