package com.imkryp70n.kryp70nnime.view.fragments.discover

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide

import com.imkryp70n.kryp70nnime.R
import com.imkryp70n.kryp70nnime.data.database.Bookmark
import com.imkryp70n.kryp70nnime.data.database.BookmarkDatabase
import com.imkryp70n.kryp70nnime.di.AesteticDialog
import com.imkryp70n.kryp70nnime.di.Injection
import com.imkryp70n.kryp70nnime.model.discovery.ResultsItem
import kotlinx.android.synthetic.main.item_anime_list.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DiscoverAdapter(private var listData: List<ResultsItem?>) :
    RecyclerView.Adapter<DiscoverAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_anime_list, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("ResourceAsColor", "SetTextI18n")
        fun bind(
            item: ResultsItem?
        ) = with(itemView) {
            tvAnimeTitle.text = item?.title
            Glide.with(context).load(item?.image).into(ivAnimeImage)
            ivAnimeImage.setOnClickListener {
                val title : String = item!!.title.toString()
                val image : String = item.image.toString()
                val url : String = item.url.toString()
                val episodeId : String = item.episodeId.toString()
                val db = Room.databaseBuilder(context, BookmarkDatabase::class.java, "bookmark").build()



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
                    AesteticDialog.toasterError(context,false,5000,"Error","Failed add to bookmark")
                }

            }
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<ResultsItem?>) {
        listData = data
        notifyDataSetChanged()
    }
}