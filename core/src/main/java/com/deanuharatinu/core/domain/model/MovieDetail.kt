package com.deanuharatinu.core.domain.model

data class MovieDetail(
  val movieId: Int,
  val title: String,
  val runTime: Int,
  val posterUrl: String,
  val rating: Float,
  val synopsis: String,
  val genres: List<String>,
  var isFavorite: Boolean =  false,
)