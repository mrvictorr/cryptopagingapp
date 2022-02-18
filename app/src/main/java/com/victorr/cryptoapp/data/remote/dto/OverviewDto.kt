package com.victorr.cryptoapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class OverviewDto(
    @SerializedName("official_links")
    val officialLinks: List<OfficialLinkDto>?,
    @SerializedName("project_details")
    val projectDetails: String,
    val tagline: String?
)