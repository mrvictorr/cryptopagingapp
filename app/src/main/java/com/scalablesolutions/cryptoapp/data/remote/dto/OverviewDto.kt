package com.scalablesolutions.cryptoapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class OverviewDto(
    val category: String,
    @SerializedName("is_verified")
    val isVerified: Boolean,
    @SerializedName("official_links")
    val officialLinks: List<OfficialLinkDto>,
    @SerializedName("project_details")
    val projectDetails: String,
    val sector: String,
    val tagline: String,
    val tags: String
)