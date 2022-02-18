package com.victorr.cryptoapp.data.datasource

import com.victorr.cryptoapp.data.remote.dto.DataDto

interface AssetsDataSource {
    suspend fun getAssets(page: Int): Collection<DataDto>
}