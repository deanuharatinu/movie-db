package com.deanuharatinu.core.domain.model

data class FavoriteMovie(
  val movieId: Int,
  val title: String,
  val photoUrl: String,
  val voteAverage: Float,
  val description: String,
)
