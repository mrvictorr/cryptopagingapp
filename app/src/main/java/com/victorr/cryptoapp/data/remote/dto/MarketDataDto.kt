package com.victorr.cryptoapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MarketDataDto(
    @SerializedName("price_btc")
    val priceBtc: Int,
    @SerializedName("price_usd")
    val priceUsd: Double
)