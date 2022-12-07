package com.imkryp70n.kryp70nnime.viewmodel.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imkryp70n.kryp70nnime.model.discovery.DiscoveryRepository

class DiscoverViewModelFactory (private val repository: DiscoveryRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DiscoverViewModel(repository) as T
    }
}