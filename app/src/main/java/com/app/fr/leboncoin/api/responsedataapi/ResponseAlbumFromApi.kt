package com.app.fr.leboncoin.api.responsedataapi

import com.squareup.moshi.Json

data class ResponseAlbumFromApi(

	@field:Json(name="albumId")
	val albumId: Int? = null,

	@field:Json(name="id")
	val id: Int? = null,

	@field:Json(name="title")
	val title: String? = null,

	@field:Json(name="url")
	val url: String? = null,

	@field:Json(name="thumbnailUrl")
	val thumbnailUrl: String? = null
)
