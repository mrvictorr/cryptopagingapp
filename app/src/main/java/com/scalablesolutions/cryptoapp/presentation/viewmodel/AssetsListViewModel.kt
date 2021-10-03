package com.scalablesolutions.cryptoapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.scalablesolutions.cryptoapp.common.Resource
import com.scalablesolutions.cryptoapp.domain.model.AssetDomain
import com.scalablesolutions.cryptoapp.domain.usecase.GetAssetsUseCase
import com.scalablesolutions.cryptoapp.presentation.model.AssetPresentation
import com.scalablesolutions.cryptoapp.presentation.model.AssetsState
import com.scalablesolutions.cryptoapp.presentation.model.toAssetPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AssetsListViewModel @Inject constructor(
    private val getAssetsUseCase: GetAssetsUseCase
) : ViewModel(){

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
        return Pager(PagingConfig(20)){ getAssetsUseCase }.flow
    }
}


