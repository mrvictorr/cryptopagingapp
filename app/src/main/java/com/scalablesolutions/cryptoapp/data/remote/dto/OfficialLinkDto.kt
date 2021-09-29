package com.scalablesolutions.cryptoapp.data.remote.dto

import com.scalablesolutions.cryptoapp.domain.model.OfficialLinkDomain

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