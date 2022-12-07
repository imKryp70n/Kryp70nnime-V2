package com.imkryp70n.kryp70nnime.view.fragments.discover

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.imkryp70n.kryp70nnime.R
import com.imkryp70n.kryp70nnime.model.discovery.ResultsItem

class DiscoverAdapter() : RecyclerView.Adapter<DiscoverAdapter.MViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val view = View.inflate(parent.context, R.layout.item_anime_list, null)
        return MViewHolder(view)
    }

    // mutable list
    private var list = mutableListOf<ResultsItem?>()

    // set data
    fun setData(data: List<ResultsItem?>) {
        list.clear()
        list.addAll(data)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvAnimeTitle: TextView = view.findViewById(R.id.tvAnimeTitle)
        private val ivAnimeImage: ImageView = view.findViewById(R.id.ivAnimeImage)
        fun bind(animeValue : ResultsItem?) {
            tvAnimeTitle.text = animeValue!!.title
            Glide.with(itemView.context)
                .load(animeValue.image)
                .into(ivAnimeImage)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<ResultsItem?>) {
        list.addAll(data)
        notifyDataSetChanged()
    }
}