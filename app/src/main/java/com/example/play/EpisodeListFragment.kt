package com.example.play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.play.databinding.ActivityMainBinding
import com.example.play.mainactivity.MainActivityViewModel
import com.example.play.viewholders.EpisodeViewHolder

class EpisodeListFragment: Fragment() {

    sealed class ViewState {
        class Loading: ViewState()
        class Error: ViewState()
        data class Loaded(val items: List<EpisodeViewHolder.ViewData>): ViewState()
    }

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        val adapter = EpisodeRecyclerViewAdapter { viewData ->
            val action = EpisodeListFragmentDirections.actionEpisodeListFragmentToEpisodeDetailedFragment()
            findNavController().navigate(action)
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

}