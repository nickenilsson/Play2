package com.example.play.models

data class Episode(
    val title: String,
    val description: String?,
    // TODO: Not optional
    val audioURL: String,
    val imageURL: String?
    )