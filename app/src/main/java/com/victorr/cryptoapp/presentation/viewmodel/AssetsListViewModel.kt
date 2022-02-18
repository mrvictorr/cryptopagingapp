package com.victorr.cryptoapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.victorr.cryptoapp.domain.model.AssetDomain
import com.victorr.cryptoapp.domain.usecase.GetAssetsSource
import com.victorr.cryptoapp.presentation.model.AssetsState
import com.victorr.cryptoapp.presentation.model.toAssetPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class AssetsListViewModel @Inject constructor(
    private val getAssetsSource: GetAssetsSource
) : ViewModel() {

    private val _state = mutableStateOf(AssetsState())
    val state: State<AssetsState> = _state

    init {
        _state.value = AssetsState(assets = getAssets().map { pagingData ->
            pagingData.map {
                it.toAssetPresentation()
            }
        })
    }

    private fun getAssets(): Flow<PagingData<AssetDomain>> {
        return Pager(PagingConfig(20)) { getAssetsSource }.flow
    }

}


