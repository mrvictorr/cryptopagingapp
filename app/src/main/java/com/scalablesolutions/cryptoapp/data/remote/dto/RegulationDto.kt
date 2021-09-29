package com.scalablesolutions.cryptoapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RegulationDto(
    @SerializedName("regulatory_details")
    val regulatoryDetails: String,
    @SerializedName("sfar_score")
    val sfarScore: Int,
    @SerializedName("sfar_summary")
    val sfarSummary: String
)