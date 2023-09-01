package com.deanuharatinu.moviedatabase.core.domain.model

data class MovieDetail(
  val title: String,
  val runTime: Int,
  val posterUrl: String,
  val rating: Float,
  val synopsis: String,
  val genres: List<String>,
)