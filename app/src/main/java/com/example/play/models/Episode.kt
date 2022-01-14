package com.example.play.models

data class Episode(
    val id: Int,
    val title: String,
    val description: String?,
    val text: String?,
    val audioURL: String,
    val imageURLSquare: String?,
    val imageURLWide: String?
    ) {
    val fullDescription: String?
    get() = "$description $text"
}