package com.imkryp70n.kryp70nnime.data.trending

import com.imkryp70n.kryp70nnime.data.ApiClient
import com.imkryp70n.kryp70nnime.model.trending.TrendingDataSource
import com.imkryp70n.kryp70nnime.model.trending.TrendingModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrendingRDS (apiClient : ApiClient) : TrendingDataSource {
    private val service = apiClient.build()
    override fun retrieveTrendingAnime(callback: OCTrending<TrendingModel>) {
        service?.topAiringAnime()?.enqueue(object : Callback<TrendingModel>{
            override fun onResponse(call: Call<TrendingModel>, response: Response<TrendingModel>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        callback.onSuccess(it)
                    }
                } else {
                    callback.onError(response.message())
                }
            }

            override fun onFailure(call: Call<TrendingModel>, t: Throwable) {
                callback.onError(t.message)
            }

        })
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }


}
