package com.victorr.cryptoapp.presentation.model

import com.victorr.cryptoapp.domain.model.OfficialLinkDomain

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