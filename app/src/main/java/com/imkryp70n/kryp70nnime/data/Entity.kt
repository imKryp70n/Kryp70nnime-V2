package com.imkryp70n.kryp70nnime.data

import com.imkryp70n.kryp70nnime.model.discovery.DiscoveryModel



data class DiscoveryResponse(val status: Int?, val msg: String?, val data: List<DiscoveryModel>?) {
    fun isSuccess(): Boolean = (status == 200)
}