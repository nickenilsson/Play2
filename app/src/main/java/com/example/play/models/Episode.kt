package com.example.play.models

data class Episode(
    val id: Int,
    val title: String,
    val description: String?,
    val audioURL: String,
    val imageURL: String?
    )