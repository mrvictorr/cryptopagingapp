package com.scalablesolutions.cryptoapp.data.datasource

import com.scalablesolutions.cryptoapp.data.remote.dto.AssetsDto
import com.scalablesolutions.cryptoapp.data.remote.dto.DataDto
import com.scalablesolutions.cryptoapp.domain.model.AssetDomain

interface AssetsDataSource {
    suspend fun getAssets(): Collection<DataDto>
}