package com.imkryp70n.kryp70nnime.data.discovery

import com.imkryp70n.kryp70nnime.model.discovery.DiscoveryModel

interface OCDiscover<T> {
    fun onSuccess(callback: DiscoveryModel)
    fun onError(error: String?)
}