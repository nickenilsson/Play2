package com.example.play.models

import java.net.URL

data class Episode(
    val title: String,
    val description: String?,
    val audioURL: URL,
    val imageURL: URL?
    )