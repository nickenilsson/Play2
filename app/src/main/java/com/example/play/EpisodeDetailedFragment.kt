package com.example.play

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.play.databinding.EpisodeDetailedViewBinding
import com.bumptech.glide.Glide
import com.example.play.models.Episode
import com.example.play.repository.EpisodeRepository

class EpisodeDetailedFragmentViewModelFactory(
    private val episodeId: Int
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodeDetailedFragmentViewModel(episodeId) as T
    }

}

class EpisodeDetailedFragmentViewModel(private val episodeId: Int): ViewModel() {

    private val episodeRepository = EpisodeRepository()

    val viewState = MutableLiveData<EpisodeDetailedFragment.ViewState>(EpisodeDetailedFragment.ViewState.Loading())
    val title = MutableLiveData<String?>(null)
    val description = MutableLiveData<String?>(null)
    val imageURL = MutableLiveData<String?>(null)

    private var episode: Episode? = null

    private var mediaPlayer: MediaPlayer? = null

    fun load() {
        viewState.postValue(EpisodeDetailedFragment.ViewState.Loading())

        episodeRepository.getEpisode(episodeId) { result ->
            result.fold({ episode ->
                this.episode = episode
                viewState.postValue(EpisodeDetailedFragment.ViewState.Loaded())
                title.postValue(episode.title)
                description.postValue(episode.fullDescription)
                imageURL.postValue(episode.imageURLWide)
            }, { exception ->
                viewState.postValue(EpisodeDetailedFragment.ViewState.Error())
            })
        }
    }

    fun play() {
        mediaPlayer?.stop()
        episode?.audioURL?.let { audioURL ->
            mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(audioURL)
                prepare() // might take long! (for buffering, etc)
                start()
            }
        }

    }

    fun pause() {
        mediaPlayer?.pause()
    }


}

class EpisodeDetailedFragment: Fragment() {

    sealed class ViewState {
        class Loading: ViewState()
        class Error: ViewState()
        class Loaded: ViewState()
    }

    private lateinit var viewModel: EpisodeDetailedFragmentViewModel

    private lateinit var binding: EpisodeDetailedViewBinding

    val args: EpisodeDetailedFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            EpisodeDetailedFragmentViewModelFactory(args.episodeId)
        ).get(EpisodeDetailedFragmentViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EpisodeDetailedViewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(this) { viewState ->
            when (viewState) {
                is ViewState.Loading -> {
                    binding.textViewLoading.isGone = false
                    binding.buttonRetry.isGone = true
                    binding.contentView.isGone = true
                }
                is ViewState.Error -> {
                    binding.buttonRetry.isGone = false
                    binding.contentView.isGone = true
                    binding.textViewLoading.isGone = true
                }
                is ViewState.Loaded -> {
                    binding.contentView.isGone = false
                    binding.buttonRetry.isGone = true
                    binding.textViewLoading.isGone = true
                }
            }
        }

        viewModel.title.observe(this) { title ->
            binding.textViewTitle.setText(title)
        }

        viewModel.description.observe(this) { description ->
            binding.textViewDescription.setText(description)
        }

        viewModel.imageURL.observe(this) { imageURL ->
            Glide.with(binding.root).load(imageURL).into(binding.imageView)
        }

        binding.buttonRetry.setOnClickListener {
            viewModel.load()
        }

        binding.buttonPlay.setOnClickListener {
            viewModel.play()
        }

        binding.buttonPause.setOnClickListener {
            viewModel.pause()
        }

        viewModel.load()

    }

}