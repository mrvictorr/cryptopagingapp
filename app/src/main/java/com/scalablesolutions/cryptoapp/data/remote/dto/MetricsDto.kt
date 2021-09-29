package com.scalablesolutions.cryptoapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MetricsDto(
    val id: String,
    @SerializedName("market_data")
    val marketData: MarketDataDto,
    val name: String,
    val slug: String,
    val symbol: String
)