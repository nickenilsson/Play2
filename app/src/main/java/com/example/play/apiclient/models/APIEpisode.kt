package com.example.play.apiclient.models

import java.net.URL

data class APIEpisode(
    val id: Int,
    val title: String,
    val description: String?,
    val listenpodfile: APIPodfile?,
    val imageurl: URL?
)

data class APIPodfile(
    val url: URL
)

data class APIEpisodesResponse(
    val episodes: List<APIEpisode>
)