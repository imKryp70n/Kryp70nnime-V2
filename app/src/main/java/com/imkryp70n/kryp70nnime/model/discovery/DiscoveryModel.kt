package com.imkryp70n.kryp70nnime.model.discovery

import com.google.gson.annotations.SerializedName

data class DiscoveryModel(

	@field:SerializedName("hasNextPage")
	val hasNextPage: Boolean? = null,

	@field:SerializedName("currentPage")
	val currentPage: Int? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null
)

data class ResultsItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("episodeId")
	val episodeId: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("episodeNumber")
	val episodeNumber: Int? = null,

	@field:SerializedName("url")
	val url: String? = null
)
