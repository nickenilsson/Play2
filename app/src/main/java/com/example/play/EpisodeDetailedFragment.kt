package com.example.play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.play.databinding.EpisodeDetailedViewBinding
import com.bumptech.glide.Glide

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
    val title: String = "P3 Dok"
    val description: String? = "episode.description"
    val imageURL: String? = "https://static-cdn.sr.se/images/909/77df221e-356c-428a-8407-102b127eb343.png"

    init {
        val test = episodeId
    }
}


class EpisodeDetailedFragment: Fragment() {
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
        binding.textViewTitle.setText(viewModel.title)
        binding.textViewDescription.setText(viewModel.description)
        Glide.with(binding.root).load(viewModel.imageURL).into(binding.imageView)
    }
    /*
    fun newInstance(episodeId: Int): EpisodeDetailedFragment {
        val args = Bundle()
        args.putInt("EPISODE_ID", episodeId)
        val fragment = EpisodeDetailedFragment()
        fragment.arguments = args
        return fragment
    }

     */
}