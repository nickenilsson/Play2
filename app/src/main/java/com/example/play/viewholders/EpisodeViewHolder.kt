package com.example.play.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.play.databinding.EpisodeViewBinding

class EpisodeViewHolder(
    val binding: EpisodeViewBinding,
    val onClickHandler: (ViewData) -> Unit)
    : RecyclerView.ViewHolder(binding.root) {

    data class ViewData(
        val title: String,
        val subtitle: String?,
        val audioURL: String,
        val imageURL: String?
        )

    fun bind(viewData: ViewData) {
        binding.textViewTitle.setText(viewData.title)
        binding.textViewSubtitle.setText(viewData.subtitle)

        Glide.with(binding.root).load(viewData.imageURL).into(binding.imageView)

        binding.root.setOnClickListener {
            onClickHandler(viewData)
        }
    }

}