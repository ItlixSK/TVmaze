package com.example.tvmaze.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tvmaze.databinding.ItemCardBinding
import com.example.tvmaze.model.TvModelItem

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvViewHolder>() {

    inner class TvViewHolder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<TvModelItem>() {
        override fun areItemsTheSame(oldItem: TvModelItem, newItem: TvModelItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvModelItem, newItem: TvModelItem): Boolean {
            return newItem == oldItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)
    var tvShows: List<TvModelItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        return TvViewHolder(
            ItemCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val currentTvShow = tvShows[position]

        holder.binding.apply {
            textName.text = currentTvShow.name

            imageView.load(currentTvShow.image.original) {
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun getItemCount() = tvShows.size
}