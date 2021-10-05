package com.scalablesolutions.cryptoapp.domain.usecase

import com.scalablesolutions.cryptoapp.domain.model.AssetDomain
import com.scalablesolutions.cryptoapp.domain.repository.AssetsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdatePriceAssetsUseCase @Inject constructor(
    private val repository: AssetsRepository
) {
    suspend operator fun invoke(): Flow<List<AssetDomain>> {
        return repository.updatePriceOfAssets()
    }
}