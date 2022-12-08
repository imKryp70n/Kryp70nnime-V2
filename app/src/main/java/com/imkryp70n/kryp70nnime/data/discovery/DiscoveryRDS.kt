package com.imkryp70n.kryp70nnime.data.discovery

import com.imkryp70n.kryp70nnime.data.ApiClient
import com.imkryp70n.kryp70nnime.data.OperationCallback
import com.imkryp70n.kryp70nnime.model.discovery.DiscoveryModel
import com.imkryp70n.kryp70nnime.model.discovery.DiscoveryDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiscoveryRDS (apiClient: ApiClient) : DiscoveryDataSource {

    private val service = apiClient.build()
    override fun retrieveDiscover(callback: OperationCallback.OCDiscover<DiscoveryModel>) {
        service?.discoverAnime()?.enqueue(object : Callback<DiscoveryModel> {
            override fun onResponse(
                call: Call<DiscoveryModel>,
                response: Response<DiscoveryModel>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback.onSuccess(it)
                    }
                } else {
                    callback.onError(response.message())
                }
            }

            override fun onFailure(call: Call<DiscoveryModel>, t: Throwable) {
                callback.onError(
                    t.message.toString()
                )
            }

        })
    }

    override fun cancel() {

    }
}
