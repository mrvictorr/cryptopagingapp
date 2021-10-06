package com.scalablesolutions.cryptoapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scalablesolutions.cryptoapp.common.Constants
import com.scalablesolutions.cryptoapp.common.Resource
import com.scalablesolutions.cryptoapp.domain.usecase.GetAssetBySymbolUseCase
import com.scalablesolutions.cryptoapp.domain.usecase.UpdatePriceAssetsUseCase
import com.scalablesolutions.cryptoapp.presentation.model.AssetDetailState
import com.scalablesolutions.cryptoapp.presentation.model.toAssetPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssetDetailViewModel @Inject constructor(
    private val getAssetBySymbolUseCase: GetAssetBySymbolUseCase,
    private val updatePriceAssetsUseCase: UpdatePriceAssetsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(AssetDetailState())
    val state: State<AssetDetailState> = _state
    private lateinit var updateJob: Job
    private var symbol = ""

    init {
        savedStateHandle.get<String>(Constants.PARAM_SYMBOL)?.let { symbol ->
            getAsset(symbol = symbol)
            this.symbol = symbol
        }
    }

    fun onStart() {
        updateJob = subscribeOnPriceUpdates(symbol = symbol)
    }

    fun onStop() {
        updateJob.cancel()
    }

    private fun getAsset(symbol: String) {
        getAssetBySymbolUseCase(symbol).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = AssetDetailState(result.data?.toAssetPresentation())
                }
                is Resource.Error -> {
                    _state.value = AssetDetailState(error = result.message ?: "Asset not found")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun subscribeOnPriceUpdates(symbol: String): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            updatePriceAssetsUseCase().collect { assetList ->
                _state.value =
                    AssetDetailState(_state.value.asset?.copy(priceUsd = assetList.first { it.symbol == symbol }.priceUsd))
            }
        }
    }

}