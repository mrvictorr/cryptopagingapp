package com.scalablesolutions.cryptoapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MarketDataDto(
    @SerializedName("ohlcv_last_1_hour")
    val ohlcvLast1Hour: OhlcvLast1HourDto,
    @SerializedName("ohlcv_last_24_hour")
    val ohlcvLast24Hour: OhlcvLast24HourDto,
    @SerializedName("percent_change_btc_last_24_hours")
    val percentChangeBtcLast24Hours: Int,
    @SerializedName("percent_change_usd_last_24_hours")
    val percentChangeUsdLast24Hours: Double,
    @SerializedName("price_btc")
    val priceBtc: Int,
    @SerializedName("price_usd")
    val priceUsd: Double,
    @SerializedName("real_volume_last_24_hours")
    val realVolumeLast24Hours: Double,
    @SerializedName("volume_last_24_hours")
    val volumeLast24Hours: Double,
    @SerializedName("volume_last_24_hours_overstatement_multiple")
    val volumeLast24HoursOverstatementMultiple: Double
)