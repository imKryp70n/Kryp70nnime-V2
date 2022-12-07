package com.imkryp70n.kryp70nnime.viewmodel.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imkryp70n.kryp70nnime.model.discovery.DiscoveryRepository
import com.imkryp70n.kryp70nnime.model.trending.TrendingRepository
import com.imkryp70n.kryp70nnime.viewmodel.discover.DiscoverViewModel
import com.imkryp70n.kryp70nnime.viewmodel.trending.TrendingViewModel

class TrendingViewModelFactory (private val repository: TrendingRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TrendingViewModel(repository) as T
    }
}