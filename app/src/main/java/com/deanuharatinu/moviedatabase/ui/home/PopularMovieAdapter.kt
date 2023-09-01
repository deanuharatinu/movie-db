package com.deanuharatinu.moviedatabase.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deanuharatinu.moviedatabase.databinding.ItemPopularMovieBinding
import com.deanuharatinu.moviedatabase.ui.home.presentation.PopularMovieUi

class PopularMovieAdapter(
  private val popularMovieUiList: MutableList<PopularMovieUi> = mutableListOf(),
  private val onClick: (String) -> Unit
) : RecyclerView.Adapter<PopularMovieAdapter.ViewHolder>() {

  @SuppressLint("NotifyDataSetChanged")
  fun submitList(popularMovieUiList: List<PopularMovieUi>) {
    this.popularMovieUiList.clear()
    this.popularMovieUiList.addAll(popularMovieUiList)
    notifyDataSetChanged()
  }

  class ViewHolder(private val binding: ItemPopularMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindTo(
      popularMovieUi: PopularMovieUi,
      onClick: (String) -> Unit
    ) {
      Glide
        .with(binding.root.context)
        .load(popularMovieUi.photoUrl)
        .into(binding.ivPopularMovie)

      binding.root.setOnClickListener {
        onClick(popularMovieUi.movieId)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val binding = ItemPopularMovieBinding.inflate(layoutInflater, parent, false)
    return ViewHolder(binding)
  }

  override fun getItemCount(): Int = popularMovieUiList.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = popularMovieUiList[position]
    holder.bindTo(item, onClick)
  }
}