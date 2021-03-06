package com.victorr.cryptoapp.data.remote.dto

import com.victorr.cryptoapp.domain.model.AssetDomain

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
        projectDetails = profile.general.overview.projectDetails,
        officialLinks = profile.general.overview.officialLinks?.map { officialLinkDto ->
            officialLinkDto.toOfficialLinkDomain()
        } ?: emptyList()
    )
}