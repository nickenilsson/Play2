package com.example.play.apiclient.models

data class APIEpisode(
    val id: Int,
    val title: String,
    val description: String?,
    var text: String? = null,
    val listenpodfile: APIPodfile?,
    val imageurl: String?,
    val imageurltemplate: String?
)

data class APIPodfile(
    val url: String
)

data class APIEpisodesResponse(
    val episodes: List<APIEpisode>
)

data class APIEpisodeResponse(
    val episode: APIEpisode
)