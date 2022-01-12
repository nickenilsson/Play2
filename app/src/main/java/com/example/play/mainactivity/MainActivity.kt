package com.example.play

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.play.databinding.ActivityMainBinding
import com.example.play.mainactivity.MainActivityViewModel
import com.example.play.viewholders.EpisodeViewHolder

class MainActivity : AppCompatActivity() {

    sealed class ViewState {
        class Loading: ViewState()
        class Error: ViewState()
        data class Loaded(val items: List<EpisodeViewHolder.ViewData>): ViewState()
    }

    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = EpisodeRecyclerViewAdapter { viewData ->
            playURL(viewData.audioURL)
        }

        binding.recyclerView.adapter = adapter

        viewModel.viewState.observe(this) { viewState ->
            when(viewState) {
                is ViewState.Loading -> {
                    binding.textView.isGone = false
                    binding.recyclerView.isGone = true
                    binding.button.isGone = true
                }
                is ViewState.Error -> {
                    binding.button.isGone = false
                    binding.recyclerView.isGone = true
                    binding.textView.isGone = true
                }
                is ViewState.Loaded -> {
                    binding.recyclerView.isGone = false
                    binding.button.isGone = true
                    binding.textView.isGone = true
                    adapter.setItems(viewState.items)
                }
            }
        }

        viewModel.load()

        binding.button.setOnClickListener {
            viewModel.load()
        }

    }

    fun playURL(url: String) {

        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            prepare() // might take long! (for buffering, etc)
            start()
        }
    }
}