package com.scalablesolutions.cryptoapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BackgroundDto(
    @SerializedName("background_details")
    val backgroundDetails: String,
    @SerializedName("issuing_organizations")
    val issuingOrganizations: List<IssuingOrganizationDto>
)