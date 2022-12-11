package com.imkryp70n.kryp70nnime.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookmarkDao {

    @Query ("SELECT * FROM bookmark")
    fun getAllBookmark(): List<Bookmark>

    // insert
    @Insert
    fun insertBookmark(bookmark: Bookmark)

    @Delete
    fun deleteBookmark(bookmark: Bookmark)
}