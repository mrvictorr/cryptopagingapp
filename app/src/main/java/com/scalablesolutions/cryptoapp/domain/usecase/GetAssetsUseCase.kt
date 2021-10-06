package com.scalablesolutions.cryptoapp.domain.usecase

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.scalablesolutions.cryptoapp.domain.model.AssetDomain
import com.scalablesolutions.cryptoapp.domain.repository.AssetsRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAssetsUseCase @Inject constructor(
    private val repository: AssetsRepository
): PagingSource<Int, AssetDomain>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AssetDomain> {
        val nextPage = params.key ?: 1
        return try {
            LoadResult.Page(
                data = repository.getAssets(nextPage), prevKey =
                if (nextPage == 1) null
                else nextPage - 1,
                nextKey = nextPage.plus(1)
            )
        } catch (e: HttpException) {
            LoadResult.Error(Exception(e.localizedMessage))
        } catch (e: IOException) {
            LoadResult.Error(Exception(e.localizedMessage))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AssetDomain>): Int? {
        return state.anchorPosition
    }

    override val keyReuseSupported: Boolean
        get() = true
}
