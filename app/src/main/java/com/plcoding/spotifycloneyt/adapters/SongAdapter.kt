package com.plcoding.spotifycloneyt.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.plcoding.spotifycloneyt.data.entities.Song
import com.plcoding.spotifycloneyt.databinding.ListItemBinding
import javax.inject.Inject

class SongAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    inner class SongViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.mediaId == newItem.mediaId
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var songs: List<Song>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.binding.tvPrimary.text = song.title
        holder.binding.tvSecondary.text = song.subtitle
        glide.load(song.imageUrl).into(holder.binding.ivItemImage)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let { click ->
                click(song)
            }
        }
    }

    override fun getItemCount() = songs.size

    private var onItemClickListener: ((Song) -> Unit)? = null

    fun setOnItemClickListener(listener: (Song) -> Unit) {
        onItemClickListener = listener
    }
}