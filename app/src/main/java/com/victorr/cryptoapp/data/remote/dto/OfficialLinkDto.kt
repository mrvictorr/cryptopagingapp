package com.victorr.cryptoapp.data.remote.dto

import com.victorr.cryptoapp.domain.model.OfficialLinkDomain

data class OfficialLinkDto(
    val link: String,
    val name: String
)

fun OfficialLinkDto.toOfficialLinkDomain(): OfficialLinkDomain{
    return OfficialLinkDomain(
        link = link,
        name = name
    )
}