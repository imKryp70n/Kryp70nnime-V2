package com.imkryp70n.kryp70nnime.viewmodel.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imkryp70n.kryp70nnime.data.discovery.OCDiscover
import com.imkryp70n.kryp70nnime.model.discovery.DiscoveryModel
import com.imkryp70n.kryp70nnime.model.discovery.DiscoveryRepository

class DiscoverViewModel(private val repository: DiscoveryRepository) : ViewModel() {

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

    fun cancel() {
        repository.cancel()
    }
}