package com.example.play.apiclient.models

import com.beust.klaxon.Klaxon
import com.beust.klaxon.KlaxonException
import com.github.kittinunf.fuel.httpGet

class APIClient {
    fun getPopularEpisodes(completionLambda: (Result<APIEpisodesResponse>) -> Unit) {
        "https://api.sr.se/api/v2/episodes/getmostlistened?format=json"
            .httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is com.github.kittinunf.result.Result.Success -> {
                        val responseString = result.get()
                        val apiEpisodesResponse = Klaxon().parse<APIEpisodesResponse>(responseString)
                        if (apiEpisodesResponse != null) {
                            completionLambda(Result.success(apiEpisodesResponse))
                        }
                        else {
                            completionLambda(Result.failure(KlaxonException("Failed to decode json")))
                        }

                    }
                    is com.github.kittinunf.result.Result.Failure -> {
                        val exception = result.getException()
                        completionLambda(Result.failure(exception))
                    }
                }

            }
    }

    fun getEpisode(episodeId: Int, completionLambda: (Result<APIEpisodeResponse>) -> Unit) {
        "https://api.sr.se/api/v2/episodes/get?id=$episodeId&format=json"
            .httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is com.github.kittinunf.result.Result.Success -> {
                        val responseString = result.get()
                        val apiEpisodeResponse = Klaxon().parse<APIEpisodeResponse>(responseString)
                        if (apiEpisodeResponse != null) {
                            completionLambda(Result.success(apiEpisodeResponse))
                        }
                        else {
                            completionLambda(Result.failure(KlaxonException("Failed to decode json")))
                        }
                    }
                    is com.github.kittinunf.result.Result.Failure -> {
                        val exception = result.getException()
                        completionLambda(Result.failure(exception))
                    }
                }
            }
    }
}