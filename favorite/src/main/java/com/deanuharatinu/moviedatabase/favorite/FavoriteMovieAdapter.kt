package com.deanuharatinu.moviedatabase.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deanuharatinu.moviedatabase.favorite.databinding.ItemFavoriteMovieBinding
import com.deanuharatinu.moviedatabase.favorite.presentation.FavoriteMovieUi

class FavoriteMovieAdapter(
  private val favoriteMovieList: MutableList<FavoriteMovieUi> = mutableListOf(),
  private val onClick: (String) -> Unit
) : RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder>() {

  @SuppressLint("NotifyDataSetChanged")
  fun submitList(favoriteMovieList: List<FavoriteMovieUi>) {
    this.favoriteMovieList.clear()
    this.favoriteMovieList.addAll(favoriteMovieList)
    notifyDataSetChanged()
  }

  class ViewHolder(private val binding: ItemFavoriteMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindTo(
      favoriteMovieUi: FavoriteMovieUi,
      onClick: (String) -> Unit
    ) {
      binding.tvMovieTitle.text = favoriteMovieUi.title
      binding.root.setOnClickListener {
        onClick(favoriteMovieUi.movieId.toString())
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    val binding = ItemFavoriteMovieBinding.inflate(layoutInflater, parent, false)
    return ViewHolder(binding)
  }

  override fun getItemCount(): Int = favoriteMovieList.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = favoriteMovieList[position]
    holder.bindTo(item, onClick)
  }
}