package com.example.play.repository

import com.example.play.apiclient.models.APIClient
import com.example.play.apiclient.models.APIEpisode
import com.example.play.apiclient.models.APIEpisodesResponse
import com.example.play.models.Episode
import java.lang.Exception

fun APIEpisode.asEpisode(): Episode? {
    if (listenpodfile?.url != null) {
        // TODO: Parse URLs
        return Episode(title, description, null, imageurl)
    } else {
        return null
    }
}

class EpisodeRepository {
    private val apiClient: APIClient = APIClient()

    fun getPopularEpisodes(completion: (Result<List<Episode>>) -> Unit) {
        apiClient.getPopularEpisodes { result: Result<APIEpisodesResponse> ->
            result.fold({ response ->
                val episodes = response.episodes.mapNotNull { apiEpisode -> apiEpisode.asEpisode() }
                completion(Result.success(episodes))
            }, { exception ->
                completion(Result.failure(exception))
            })
        }
    }
}