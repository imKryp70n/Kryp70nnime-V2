package com.imkryp70n.kryp70nnime.view.fragments.history

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.imkryp70n.kryp70nnime.R
import com.imkryp70n.kryp70nnime.data.database.Bookmark
import com.imkryp70n.kryp70nnime.model.discovery.ResultsItem
import com.zedlabs.pastelplaceholder.Pastel

class HistoryAdapter(private var listData: List<Bookmark>) : RecyclerView.Adapter<HistoryAdapter.MViewHolder>() {



    class MViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvAnimeTitle: TextView = view.findViewById(R.id.tvAnimeTitle)
        private val ivAnimeImage: ImageView = view.findViewById(R.id.ivAnimeImage)
        private val tvAnimeStatus: TextView = view.findViewById(R.id.tvAnimeStatus)

        fun bind(item : Bookmark, position: Int) {
            tvAnimeTitle.text = item.title
            Glide.with(itemView.context)
                .load(item.image)
                .placeholder(Pastel.getColorLight())
                .transition(DrawableTransitionOptions.withCrossFade(300))
                .into(ivAnimeImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val view = View.inflate(parent.context, R.layout.item_anime_list, null)
        return MViewHolder(view)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.bind(listData[position], position)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}