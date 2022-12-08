package com.imkryp70n.kryp70nnime.model.trending

import com.imkryp70n.kryp70nnime.data.OperationCallback

interface TrendingDataSource {
    fun retrieveTrendingAnime(callback: OperationCallback.OCTrending<TrendingModel>)
    fun cancel()
}