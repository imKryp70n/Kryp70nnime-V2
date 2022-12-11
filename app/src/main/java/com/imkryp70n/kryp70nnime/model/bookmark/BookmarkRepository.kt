package com.imkryp70n.kryp70nnime.model.bookmark

import com.imkryp70n.kryp70nnime.data.OperationCallback
import com.imkryp70n.kryp70nnime.data.database.Bookmark

class BookmarkRepository  (private val bookmarkDataSource: BookmarkDataSource) {
    fun fetchHistory(callback: OperationCallback.OCHistory<Bookmark>) {
        bookmarkDataSource.retrieveHistory(callback)
    }

    fun insertBookmark(bookmark: Bookmark, callback: OperationCallback.OCHistory<Bookmark>) {
    }

    fun cancel() {
        bookmarkDataSource.cancel()
    }

}