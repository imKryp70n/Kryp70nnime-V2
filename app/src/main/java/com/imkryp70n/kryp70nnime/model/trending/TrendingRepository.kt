package com.imkryp70n.kryp70nnime.model.trending
import com.imkryp70n.kryp70nnime.data.OperationCallback

class TrendingRepository (private val trendingDataSource: TrendingDataSource) {

    fun fetchTrending(callback: OperationCallback.OCTrending<TrendingModel>) {
        trendingDataSource.retrieveTrendingAnime(callback)
    }

    fun cancel() {
        trendingDataSource.cancel()
    }
}