package com.imkryp70n.kryp70nnime.model.discovery

import com.imkryp70n.kryp70nnime.data.OperationCallback

interface DiscoveryDataSource {
    fun retrieveDiscover(callback: OperationCallback.OCDiscover<DiscoveryModel>)
    fun cancel()
}