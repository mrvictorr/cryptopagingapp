package com.victorr.cryptoapp.domain.repository

import com.victorr.cryptoapp.domain.model.AssetDomain

interface AssetsRepository {
    suspend fun getAssets(page: Int): List<AssetDomain>
}