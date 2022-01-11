package com.example.play

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.play.databinding.ActivityMainBinding
import com.example.play.viewholders.EpisodeViewHolder

class MainActivityViewModel: ViewModel() {
    val listItems: List<EpisodeViewHolder.ViewData> = listOf(
        EpisodeViewHolder.ViewData("Episode 1", "Subtitle episode", null),
        EpisodeViewHolder.ViewData("Episode 2", "Subtitle episode", null),
        EpisodeViewHolder.ViewData("Episode 3", "Subtitle episode", null),
        EpisodeViewHolder.ViewData("Episode 4", "Subtitle episode", null),
        EpisodeViewHolder.ViewData("Episode 5", "Subtitle episode", null),
        EpisodeViewHolder.ViewData("Episode 6", "Subtitle episode", null),
    )
}

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = EpisodeRecyclerViewAdapter(viewModel.listItems)

    }
}