package com.example.play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.play.databinding.EpisodeDetailedViewBinding
import com.example.play.models.Episode
import com.bumptech.glide.Glide

class EpisodeDetailedFragmentViewModel: ViewModel(){
    val title: String = "P3 Dok"
    val description: String? = "episode.description"
    val imageURL: String? = "https://static-cdn.sr.se/images/909/77df221e-356c-428a-8407-102b127eb343.png"
}

class EpisodeDetailedFragment: Fragment() {
    private val viewModel: EpisodeDetailedFragmentViewModel by viewModels()

    private lateinit var binding: EpisodeDetailedViewBinding

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
        binding.textViewTitle.setText(viewModel.title)
        binding.textViewDescription.setText(viewModel.description)
        Glide.with(binding.root).load(viewModel.imageURL).into(binding.imageView)
    }
}