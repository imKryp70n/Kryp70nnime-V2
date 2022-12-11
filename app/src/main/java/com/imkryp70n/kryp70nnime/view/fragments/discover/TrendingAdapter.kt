package com.imkryp70n.kryp70nnime.view.fragments.discover

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.imkryp70n.kryp70nnime.R
import com.imkryp70n.kryp70nnime.data.database.Bookmark
import com.imkryp70n.kryp70nnime.data.database.BookmarkDatabase
import com.imkryp70n.kryp70nnime.di.AesteticDialog
import com.imkryp70n.kryp70nnime.model.discovery.ResultsItem
import com.imkryp70n.kryp70nnime.model.trending.TrendingItem
import com.zedlabs.pastelplaceholder.Pastel
import kotlinx.android.synthetic.main.item_anime_list.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
        fun bind(item : TrendingItem?)  = with(itemView){

            tvAnimeTitle.text = item!!.title
            Glide.with(itemView.context)
                .load(item.image)
                .placeholder(Pastel.getColorLight())
                .into(ivAnimeImage)

            ivAnimeImage.setOnLongClickListener {
                val title : String = item.title.toString()
                val image : String = item.image.toString()
                val url : String = item.url.toString()
                val episodeId : String = item.id.toString()
                val db = Room.databaseBuilder(context, BookmarkDatabase::class.java, "bookmark").build()


                AesteticDialog.sweetWarning(
                    context,
                    "Add Bookmark",
                    "Are you sure to add this bookmark?",
                    "Yes",
                    "No",
                    {
                        try {
                            GlobalScope.launch {
                                db.bookmarkDao().insertBookmark(
                                    Bookmark(
                                        title,
                                        url,
                                        image,
                                        episodeId
                                    )
                                )
                                println(
                                    db.bookmarkDao().getAllBookmark()
                                )
                            }

                            AesteticDialog.toasterSuccess(
                                context,
                                false,
                                3000,
                                "Success",
                                "Success add to bookmark"
                            )
                        } catch (e: Exception) {
                            AesteticDialog.toasterError(
                                context,
                                false,
                                5000,
                                "Error",
                                "Failed add to bookmark"
                            )
                        }
                    },
                    {
                        it.dismissWithAnimation()
                    })
                true
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<TrendingItem?>) {
        listData = data
        notifyDataSetChanged()
    }
}