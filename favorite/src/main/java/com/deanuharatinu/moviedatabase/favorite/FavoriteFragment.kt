package com.deanuharatinu.moviedatabase.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.deanuharatinu.moviedatabase.favorite.databinding.FragmentFavoriteBinding
import com.deanuharatinu.moviedatabase.favorite.di.DaggerFavoriteComponent
import com.deanuharatinu.moviedatabase.favorite.presentation.FavoriteViewModel
import com.deanuharatinu.moviedatabase.ui.di.FavoriteModule
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {
  private var _binding: FragmentFavoriteBinding? = null
  private val binding get() = _binding!!

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

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}