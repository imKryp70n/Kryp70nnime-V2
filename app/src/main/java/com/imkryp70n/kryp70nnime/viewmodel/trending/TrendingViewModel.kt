package com.imkryp70n.kryp70nnime.viewmodel.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imkryp70n.kryp70nnime.data.OperationCallback
import com.imkryp70n.kryp70nnime.model.trending.TrendingModel
import com.imkryp70n.kryp70nnime.model.trending.TrendingRepository

class TrendingViewModel(private val repository: TrendingRepository) : ViewModel() {

    private val _getTrending = MutableLiveData<TrendingModel>()
    val getTrending: LiveData<TrendingModel> = _getTrending

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any?>()
    val onMessageError: MutableLiveData<Any?> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    fun getTrending() {
        _isViewLoading.postValue(true)
        repository.fetchTrending(object : OperationCallback.OCTrending<TrendingModel> {
            override fun onSuccess(callback: TrendingModel) {
                _isViewLoading.postValue(false)
                _getTrending.postValue(callback)
            }

            override fun onError(error: String?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue(error)
            }
        })
    }
}