package com.scalablesolutions.cryptoapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MetricsDto(
    @SerializedName("market_data")
    val marketData: MarketDataDto
)