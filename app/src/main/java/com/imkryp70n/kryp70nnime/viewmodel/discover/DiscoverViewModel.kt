package com.imkryp70n.kryp70nnime.viewmodel.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imkryp70n.kryp70nnime.data.discovery.OCDiscover
import com.imkryp70n.kryp70nnime.data.trending.OCTrending
import com.imkryp70n.kryp70nnime.model.discovery.DiscoveryModel
import com.imkryp70n.kryp70nnime.model.discovery.DiscoveryRepository
import com.imkryp70n.kryp70nnime.model.trending.TrendingModel
import com.imkryp70n.kryp70nnime.model.trending.TrendingRepository

class DiscoverViewModel(private val repository: DiscoveryRepository , private val trendingRepository: TrendingRepository) : ViewModel() {

    private val _discoveries = MutableLiveData<DiscoveryModel>()
    val discoveries: LiveData<DiscoveryModel> = _discoveries

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any?>()
    val onMessageError: MutableLiveData<Any?> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList



    fun loadDiscover() {
        repository.fetchDiscover(object : OCDiscover<DiscoveryModel> {

            override fun onError(error: String?) {
                _onMessageError.postValue(error)
                _isViewLoading.postValue(false)
            }

            override fun onSuccess(callback: DiscoveryModel) {
                _isViewLoading.value = true
                if(callback.results!!.isEmpty()) {
                    _isEmptyList.value = true
                } else {
                    _discoveries.value = callback
                }
            }
        })
    }


    private val _getTrending = MutableLiveData<TrendingModel>()
    val getTrending: LiveData<TrendingModel> = _getTrending

    private val _isTrendingViewLoading = MutableLiveData<Boolean>()
    val isViewLoadingTrending: LiveData<Boolean> = _isTrendingViewLoading

    private val _onTrendingMessageError = MutableLiveData<Any?>()
    val onTrendingMessageError: MutableLiveData<Any?> = _onTrendingMessageError

    private val _isTrendingEmptyList = MutableLiveData<Boolean>()
    val isTrendingEmptyList: LiveData<Boolean> = _isTrendingEmptyList

    fun loadTrending(){
        trendingRepository.fetchTrending(object : OCTrending<TrendingModel>{
            override fun onSuccess(callback: TrendingModel) {
                if (callback.results!!.isEmpty()){
                    _isTrendingEmptyList.value = true
                } else {
                    _getTrending.value = callback
                }
            }

            override fun onError(error: String?) {
                _onTrendingMessageError.value = error
                _isTrendingViewLoading.value = false
            }
        })
    }

    fun cancel() {
        repository.cancel()
    }
}