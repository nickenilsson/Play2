package com.example.play.mainactivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.play.repository.EpisodeRepository
import com.example.play.viewholders.EpisodeViewHolder

class MainActivityViewModel: ViewModel() {

    private val repository = EpisodeRepository()

    val listItems = MutableLiveData(listOf(
        EpisodeViewHolder.ViewData("Episode 1", "Episode 1 subtitle", null),
        EpisodeViewHolder.ViewData("Episode 2", "Episode 2 subtitle", null),
        EpisodeViewHolder.ViewData("Episode 3", "Episode 3 subtitle", null)
    ))

    fun load() {
        repository.getPopularEpisodes { result ->
            result.fold({ episodes ->
                listItems.postValue(episodes.map { episode ->
                    EpisodeViewHolder.ViewData(episode.title, episode.description, episode.imageURL)
                })
            }, { exception ->
                listItems.postValue(listOf())
            })
        }
    }
}