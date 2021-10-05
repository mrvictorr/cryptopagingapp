package com.scalablesolutions.cryptoapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.scalablesolutions.cryptoapp.domain.model.AssetDomain
import com.scalablesolutions.cryptoapp.domain.usecase.GetAssetsUseCase
import com.scalablesolutions.cryptoapp.domain.usecase.UpdatePriceAssetsUseCase
import com.scalablesolutions.cryptoapp.presentation.model.AssetPresentation
import com.scalablesolutions.cryptoapp.presentation.model.AssetsState
import com.scalablesolutions.cryptoapp.presentation.model.toAssetPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssetsListViewModel @Inject constructor(
    private val getAssetsUseCase: GetAssetsUseCase,
    private val updatePriceAssetsUseCase: UpdatePriceAssetsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AssetsState())
    val state: State<AssetsState> = _state

    private val _updatePriceState = mutableStateOf(UpdatePriceState())
    val updatePriceState: State<UpdatePriceState> = _updatePriceState

    init {
        _state.value = AssetsState(assets = getAssets().map { pagingData ->
            pagingData.map {
                it.toAssetPresentation()
            }
        })
        subscribeOnPriceUpdates()
    }

    private fun getAssets(): Flow<PagingData<AssetDomain>> {
        return Pager(PagingConfig(20)) { getAssetsUseCase }.flow
    }

    private fun subscribeOnPriceUpdates() {
        viewModelScope.launch(Dispatchers.IO) {
            updatePriceAssetsUseCase().collect {
                _updatePriceState.value =
                    UpdatePriceState(it.map { item -> item.toAssetPresentation() })
            }
        }
    }
}

data class UpdatePriceState(
    val assets: List<AssetPresentation> = emptyList()
)


