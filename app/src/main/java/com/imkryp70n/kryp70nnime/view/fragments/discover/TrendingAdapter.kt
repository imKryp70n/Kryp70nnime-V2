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
import com.imkryp70n.kryp70nnime.model.trending.TrendingItem

class TrendingAdapter (private var listData : List<TrendingItem?>) : RecyclerView.Adapter<TrendingAdapter.MViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val view = View.inflate(parent.context, R.layout.item_anime_list, null)
        return MViewHolder(view)
    }

    // mutable list

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class MViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvAnimeTitle: TextView = view.findViewById(R.id.tvAnimeTitle)
        private val ivAnimeImage: ImageView = view.findViewById(R.id.ivAnimeImage)
        private val tvAnimeStatus: TextView = view.findViewById(R.id.tvAnimeStatus)
        fun bind(animeValue : TrendingItem?) {

            tvAnimeTitle.text = animeValue!!.title
            Glide.with(itemView.context)
                .load(animeValue.image)
                .into(ivAnimeImage)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<TrendingItem?>) {
        listData = data
        notifyDataSetChanged()
    }
}