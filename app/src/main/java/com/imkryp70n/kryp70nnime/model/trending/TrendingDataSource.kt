package com.imkryp70n.kryp70nnime.model.trending

import com.imkryp70n.kryp70nnime.data.trending.OCTrending

interface TrendingDataSource {
    fun retrieveTrendingAnime(callback: OCTrending<TrendingModel>)
    fun cancel()
}