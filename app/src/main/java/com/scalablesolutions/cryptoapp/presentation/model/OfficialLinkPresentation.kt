package com.scalablesolutions.cryptoapp.presentation.model

import com.scalablesolutions.cryptoapp.domain.model.OfficialLinkDomain

data class OfficialLinkPresentation (
    val link: String,
    val name: String
)

fun OfficialLinkDomain.toOfficialLinkPresentation(): OfficialLinkPresentation{
    return OfficialLinkPresentation(
        link = link ?: "",
        name = name ?: ""
    )
}