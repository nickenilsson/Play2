package com.example.play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.play.databinding.EpisodeDetailedViewBinding

class EpisodeDetailedFragment: Fragment() {

    private lateinit var binding: EpisodeDetailedViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EpisodeDetailedViewBinding.inflate(layoutInflater)
        return binding.root
    }
}