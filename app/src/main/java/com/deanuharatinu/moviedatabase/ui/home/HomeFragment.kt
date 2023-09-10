package com.deanuharatinu.moviedatabase.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.deanuharatinu.core.utils.IntentConstants
import com.deanuharatinu.moviedatabase.databinding.FragmentHomeBinding
import com.deanuharatinu.moviedatabase.ui.home.presentation.HomeViewModel
import com.deanuharatinu.moviedatabase.ui.home.presentation.ViewModelState
import com.deanuharatinu.moviedatabase.ui.moviedetail.MovieDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
  private var _binding: FragmentHomeBinding? = null
  private val binding get() = _binding!!
  private val homeViewModel: HomeViewModel by viewModels()
  private lateinit var adapter: PopularMovieAdapter

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initRecyclerView()
    initDataObserver()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun initRecyclerView() {
    adapter = PopularMovieAdapter { movieId ->
      val intent = Intent(requireActivity(), MovieDetailActivity::class.java)
      intent.putExtra(IntentConstants.MOVIE_ID, movieId)
      startActivity(intent)
    }
    binding.rvPopularMovie.adapter = adapter
  }

  private fun initDataObserver() {
    lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.STARTED) {
        homeViewModel.uiState.collect(::loadContent)
      }
    }
  }

  private fun loadContent(viewModelState: ViewModelState) {
    if (viewModelState.isLoading) {
      binding.loading.visibility = View.VISIBLE
    } else {
      binding.loading.visibility = View.GONE
      adapter.submitList(viewModelState.popularMovie)
    }
  }
}