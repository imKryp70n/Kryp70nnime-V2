package com.imkryp70n.kryp70nnime.data.trending

import com.imkryp70n.kryp70nnime.model.discovery.DiscoveryModel
import com.imkryp70n.kryp70nnime.model.trending.TrendingModel

interface OCTrending <T> {
    fun onSuccess(callback: TrendingModel)
    fun onError(error: String?)
}