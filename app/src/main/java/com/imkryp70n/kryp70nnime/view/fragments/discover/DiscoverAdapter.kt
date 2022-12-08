package com.imkryp70n.kryp70nnime.view.fragments.discover

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.facebook.shimmer.ShimmerFrameLayout
import com.imkryp70n.kryp70nnime.R
import com.imkryp70n.kryp70nnime.model.discovery.ResultsItem
import com.zedlabs.pastelplaceholder.Pastel

class DiscoverAdapter(private var listData : List<ResultsItem?>) : RecyclerView.Adapter<DiscoverAdapter.MViewHolder>() {

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
        @SuppressLint("ResourceType")
        fun bind(animeValue : ResultsItem?) {

            // shimmer animation
            tvAnimeTitle.text = animeValue!!.title
            tvAnimeStatus.text = animeValue.episodeNumber.toString()
            Glide.with(itemView.context)
                .load(animeValue.image)
                .placeholder(Pastel.getColorLight())
                .transition(DrawableTransitionOptions.withCrossFade(300))
                .into(ivAnimeImage)

            // stop shimmer animation
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<ResultsItem?>) {
        listData = data
        notifyDataSetChanged()
    }
}