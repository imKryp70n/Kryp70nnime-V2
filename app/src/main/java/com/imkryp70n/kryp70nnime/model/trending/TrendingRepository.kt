package com.imkryp70n.kryp70nnime.model.trending
import com.imkryp70n.kryp70nnime.data.trending.OCTrending

class TrendingRepository (private val trendingDataSource: TrendingDataSource) {

    fun fetchTrending(callback: OCTrending<TrendingModel>) {
        trendingDataSource.retrieveTrendingAnime(callback)
    }

    fun cancel() {
        trendingDataSource.cancel()
    }
}