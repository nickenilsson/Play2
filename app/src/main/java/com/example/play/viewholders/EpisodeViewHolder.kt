package com.example.play.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.play.databinding.EpisodeViewBinding
import java.net.URL

class EpisodeViewHolder(val binding: EpisodeViewBinding): RecyclerView.ViewHolder(binding.root) {

    data class ViewData(
        val title: String,
        val subtitle: String?,
        val imageURL: URL?
        )
    
    fun bind(viewData: ViewData) {
        binding.textViewTitle.setText(viewData.title)
        binding.textViewSubtitle.setText(viewData.subtitle)
        // TODO: Load image from url
    }

}