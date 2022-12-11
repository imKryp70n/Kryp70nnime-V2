package com.imkryp70n.kryp70nnime.viewmodel.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imkryp70n.kryp70nnime.model.bookmark.BookmarkRepository
import com.imkryp70n.kryp70nnime.viewmodel.bookmark.BookmarkViewModel

class BookmarkViewModelFactory (private val repository: BookmarkRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BookmarkViewModel(repository) as T
    }
}