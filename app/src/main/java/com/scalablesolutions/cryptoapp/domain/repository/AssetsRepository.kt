package com.scalablesolutions.cryptoapp.domain.repository

import com.scalablesolutions.cryptoapp.domain.model.AssetDomain

interface AssetsRepository {
    suspend fun getAssets(): List<AssetDomain>
}