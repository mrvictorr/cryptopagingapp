package com.scalablesolutions.cryptoapp.common

object Constants {

    const val BASE_URL = "https://data.messari.io/"
    const val GET_ASSETS_FIELDS =
        "name,symbol,metrics/market_data/price_usd,profile/general/overview/tagline,profile/general/overview/project_details,profile/general/overview/official_links"
    const val UPDATE_ASSETS_FIELDS = "symbol,metrics/market_data/price_usd"
    const val PARAM_SYMBOL = "symbolId"

    //we can send 20 request per minute, I decide update 6 times in minute, and left 12 request to upload pages.
    //PS we have 1000 per day, so we can request once in 96 seconds, but it's too rare for test application
    const val UPDATE_PRICE_TIME: Long = 10000

}