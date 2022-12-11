package com.imkryp70n.kryp70nnime.data.bookmark

import android.content.Context
import com.imkryp70n.kryp70nnime.data.OperationCallback
import com.imkryp70n.kryp70nnime.data.database.Bookmark
import com.imkryp70n.kryp70nnime.data.database.BookmarkDao
import com.imkryp70n.kryp70nnime.data.database.BookmarkDatabase
import com.imkryp70n.kryp70nnime.model.bookmark.BookmarkDataSource

class BookmarkRDS(context: Context) : BookmarkDataSource{

    private val bookmarkDao: BookmarkDao = BookmarkDatabase.getInstance(context).bookmarkDao()
    override fun retrieveHistory(callback: OperationCallback.OCHistory<Bookmark>) {
        callback.onSuccess(bookmarkDao.getAllBookmark())
    }

    override fun cancel() {
    }



}