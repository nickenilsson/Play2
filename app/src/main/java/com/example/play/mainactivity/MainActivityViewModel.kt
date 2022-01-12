package com.example.play.mainactivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.play.EpisodeListFragment
import com.example.play.MainActivity
import com.example.play.repository.EpisodeRepository
import com.example.play.viewholders.EpisodeViewHolder

class MainActivityViewModel: ViewModel() {

    private val repository = EpisodeRepository()

    val viewState: MutableLiveData<EpisodeListFragment.ViewState> = MutableLiveData(EpisodeListFragment.ViewState.Loading())

    fun load() {
        repository.getPopularEpisodes { result ->
            result.fold({ episodes ->
                val viewData = episodes.map { episode ->
                    EpisodeViewHolder.ViewData(
                        episode.title,
                        episode.description,
                        episode.audioURL,
                        episode.imageURL
                    )
                }
                viewState.postValue(EpisodeListFragment.ViewState.Loaded(viewData))
            }, { exception ->
                viewState.postValue(EpisodeListFragment.ViewState.Error())
            })
        }
    }
}