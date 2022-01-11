package com.example.play.mainactivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.play.viewholders.EpisodeViewHolder

class MainActivityViewModel: ViewModel() {
    val listItems = MutableLiveData(listOf(
        EpisodeViewHolder.ViewData("Episode 1", "Episode 1 subtitle", null),
        EpisodeViewHolder.ViewData("Episode 2", "Episode 2 subtitle", null),
        EpisodeViewHolder.ViewData("Episode 3", "Episode 3 subtitle", null)
    ))

    fun load() {

    }
}