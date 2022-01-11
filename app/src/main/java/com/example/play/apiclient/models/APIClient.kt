package com.example.play.apiclient.models

import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import java.lang.Exception


class APIClient {
    fun getPopularEpisodes(completionLambda: (Result<APIEpisodesResponse>) -> Unit) {
        "https://api.sr.se/api/v2/episodes/getmostlistened?format=json"
            .httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is com.github.kittinunf.result.Result.Success -> {
                        val responseString = result.get()
                        val apiEpisodesResponse = Klaxon().parse<APIEpisodesResponse>(responseString)
                        if (apiEpisodesResponse != null){
                            completionLambda(Result.success(apiEpisodesResponse))
                        }
                        else {
                            // TODO: Use specific exception
                            completionLambda(Result.failure(Exception()))
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