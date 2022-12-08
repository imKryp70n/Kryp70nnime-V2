package com.imkryp70n.kryp70nnime.model.discovery

import com.imkryp70n.kryp70nnime.data.OperationCallback

class DiscoveryRepository (private val discoveryDataSource: DiscoveryDataSource) {

    fun fetchDiscover(callback: OperationCallback.OCDiscover<DiscoveryModel>) {
        discoveryDataSource.retrieveDiscover(callback)
    }

    fun cancel() {
        discoveryDataSource.cancel()
    }
}