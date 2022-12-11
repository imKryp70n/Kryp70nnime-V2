package com.imkryp70n.kryp70nnime.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bookmark(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "episodeId") val episodeId: String,
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
