package com.imkryp70n.kryp70nnime.data

import com.imkryp70n.kryp70nnime.model.discovery.DiscoveryModel
import com.imkryp70n.kryp70nnime.model.trending.TrendingModel

class OperationCallback {
    interface OCDiscover<T> {
        fun onSuccess(callback: DiscoveryModel)
        fun onError(error: String?)
    }

    interface OCTrending <T> {
        fun onSuccess(callback: TrendingModel)
        fun onError(error: String?)
    }

    interface OCHistory<T> {
        fun onSuccess(callback: List<T>)
        fun onError(error: String?)

    }
}