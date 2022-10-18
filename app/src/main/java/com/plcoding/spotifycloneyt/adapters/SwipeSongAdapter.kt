package com.plcoding.spotifycloneyt.adapters

import androidx.recyclerview.widget.AsyncListDiffer
import com.plcoding.spotifycloneyt.R
import kotlinx.android.synthetic.main.swipe_item.view.*

class SwipeSongAdapter : BaseSongAdapter(R.layout.swipe_item) {

    override var differ = AsyncListDiffer(this, diffCallback)

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.itemView.apply {
            tvTitleSwipe.text = song.title
            tvSubtitleSwipe.text = song.subtitle

            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(song)
                }
            }
        }
    }
}