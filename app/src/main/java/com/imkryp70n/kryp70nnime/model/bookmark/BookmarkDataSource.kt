package com.imkryp70n.kryp70nnime.model.bookmark

import com.imkryp70n.kryp70nnime.data.OperationCallback
import com.imkryp70n.kryp70nnime.data.database.Bookmark

interface BookmarkDataSource {
    fun retrieveHistory(callback : OperationCallback.OCHistory<Bookmark>)
    fun cancel()
}