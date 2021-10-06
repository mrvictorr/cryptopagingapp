package com.scalablesolutions.cryptoapp.data.datasource

import com.scalablesolutions.cryptoapp.common.Constants
import com.scalablesolutions.cryptoapp.common.Constants.UPDATE_PRICE_TIME
import com.scalablesolutions.cryptoapp.data.remote.MessariApi
import com.scalablesolutions.cryptoapp.data.remote.dto.DataDto
import com.scalablesolutions.cryptoapp.data.remote.dto.MarketDataDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AssetsDataSourceImpl @Inject constructor(
    private val api: MessariApi
): AssetsDataSource {

    //cache
    private var assetsList: Collection<DataDto> = mutableListOf()

    override suspend fun getAssets(page: Int): Collection<DataDto> {
        return if (assetsList.size % 20 == 0) {
            if (page * 20 > assetsList.size) {
                val currentPage = assetsList.size / 20 + 1
                val assetsDto = api.getAssets(currentPage.toString(), Constants.GET_ASSETS_FIELDS)
                (assetsList as MutableList).addAll(assetsDto.data)
                assetsDto.data
            } else {
                val list = (assetsList as MutableList).slice((page - 1) * 20 until page * 20)
                list
            }
        } else {
            emptyList()
        }
    }

    override fun getAssetBySymbol(symbol: String): DataDto? {
        return assetsList.firstOrNull {
            it.symbol == symbol
        }
    }

    override suspend fun updatePriceOfAssets(): Flow<Collection<DataDto>> {
        return flow {
            delay(UPDATE_PRICE_TIME)
            while (true) {
                if (assetsList.isNotEmpty()) {
                    val assetsDto =
                        api.getAssets(
                            "1",
                            Constants.UPDATE_ASSETS_FIELDS,
                            assetsList.size.toString()
                        )
                    assetsDto.data.forEach { updatedData ->
                        val replacedIndex = assetsList.indexOfFirst { originData ->
                            updatedData.symbol == originData.symbol
                        }
                        (assetsList as MutableList)[replacedIndex] =
                            (assetsList as MutableList)[replacedIndex].copy(
                                metrics = (assetsList as MutableList)[replacedIndex].metrics.copy(
                                    marketData = MarketDataDto(
                                        priceUsd = updatedData.metrics.marketData.priceUsd,
                                        priceBtc = updatedData.metrics.marketData.priceBtc
                                    )
                                )
                            )
                    }
                }
                emit(assetsList)
                delay(UPDATE_PRICE_TIME)
            }
        }
    }

}