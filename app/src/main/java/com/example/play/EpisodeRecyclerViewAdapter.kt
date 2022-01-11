package com.example.play

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.play.databinding.EpisodeViewBinding
import com.example.play.viewholders.EpisodeViewHolder

class EpisodeRecyclerViewAdapter(val items: List<EpisodeViewHolder.ViewData>): RecyclerView.Adapter<EpisodeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = EpisodeViewBinding.inflate(layoutInflater, parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val viewData = items[position]
        holder.bind(viewData)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}