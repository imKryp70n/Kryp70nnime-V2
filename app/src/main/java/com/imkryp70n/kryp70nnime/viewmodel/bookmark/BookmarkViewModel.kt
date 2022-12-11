package com.imkryp70n.kryp70nnime.viewmodel.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imkryp70n.kryp70nnime.data.OperationCallback
import com.imkryp70n.kryp70nnime.data.database.Bookmark
import com.imkryp70n.kryp70nnime.data.database.BookmarkDatabase
import com.imkryp70n.kryp70nnime.model.bookmark.BookmarkRepository

class BookmarkViewModel(private val repository: BookmarkRepository) : ViewModel() {

    private val _bookmarks = MutableLiveData<List<Bookmark>>()
    val bookmarks: LiveData<List<Bookmark>> = _bookmarks

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any?>()
    val onMessageError: MutableLiveData<Any?> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    lateinit var db: BookmarkDatabase


    fun getBookmarks() {
        _isViewLoading.postValue(true)
        repository.fetchHistory(object : OperationCallback.OCHistory<Bookmark>{
            override fun onSuccess(callback: List<Bookmark>) {
                _isViewLoading.postValue(false)
                if (callback.isEmpty()) {
                    _isEmptyList.postValue(true)
                } else {
                    _bookmarks.postValue(callback)
                    _isEmptyList.postValue(false)
                }
            }

            override fun onError(error: String?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue(error)
            }
        })
    }

}