package com.scalablesolutions.cryptoapp.data.remote.dto

import com.scalablesolutions.cryptoapp.domain.model.AssetDomain

data class DataDto(
    val metrics: MetricsDto,
    val name: String,
    val profile: ProfileDto,
    val symbol: String?
)

fun DataDto.toAssetDomain(): AssetDomain {
    return AssetDomain(
        name = name,
        symbol = symbol ?: "",
        priceUsd = metrics.marketData.priceUsd,
        tagline = profile.general.overview.tagline,
        officialLinks = profile.general.overview.officialLinks?.map { officialLinkDto ->
            officialLinkDto.toOfficialLinkDomain()
        } ?: emptyList()
    )
}