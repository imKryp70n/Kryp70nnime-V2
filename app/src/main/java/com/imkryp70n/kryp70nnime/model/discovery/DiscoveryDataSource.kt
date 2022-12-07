package com.imkryp70n.kryp70nnime.model.discovery

import com.imkryp70n.kryp70nnime.data.discovery.OCDiscover

interface DiscoveryDataSource {
    fun retrieveDiscover(callback: OCDiscover<DiscoveryModel>)
    fun cancel()
}