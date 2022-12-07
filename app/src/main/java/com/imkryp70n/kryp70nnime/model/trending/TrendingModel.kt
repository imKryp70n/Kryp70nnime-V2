package com.imkryp70n.kryp70nnime.model.trending

import com.google.gson.annotations.SerializedName

data class TrendingModel(

	@field:SerializedName("hasNextPage")
	val hasNextPage: Boolean? = null,

	@field:SerializedName("currentPage")
	val currentPage: Int? = null,

	@field:SerializedName("results")
	val results: List<TrendingItem?>? = null
)

data class TrendingItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("genres")
	val genres: List<String?>? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
