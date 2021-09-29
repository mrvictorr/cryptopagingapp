package com.scalablesolutions.cryptoapp.data.remote.dto


data class ProfileDto(
    val profile: ProfileXDto
)

data class ProfileXDto(
    val general: GeneralDto
)