package com.example.play.repository

import com.example.play.apiclient.models.APIClient
import com.example.play.apiclient.models.APIEpisode
import com.example.play.apiclient.models.APIEpisodeResponse
import com.example.play.apiclient.models.APIEpisodesResponse
import com.example.play.models.Episode

fun APIEpisode.asEpisode(): Episode? {
    if (listenpodfile?.url != null) {
        val imageUrlWide = imageurltemplate?.let {
            it + "?preset=api-fullwidth-medium"
        }
        return Episode(id, title, description, listenpodfile.url, imageurl, imageUrlWide)
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

    fun getEpisode(episodeId: Int, completion: (Result<Episode>) -> Unit) {
        apiClient.getEpisode(episodeId) { result: Result<APIEpisodeResponse> ->
            result.fold({ response ->
                val episode = response.episode.asEpisode()
                if (episode != null) {
                    completion(Result.success(episode))
                } else {
                    completion(Result.failure(Exception()))
                }

            }, { exception ->
                completion(Result.failure(exception))
            })
        }

    }
}