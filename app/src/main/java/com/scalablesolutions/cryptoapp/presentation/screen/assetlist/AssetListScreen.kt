package com.scalablesolutions.cryptoapp.presentation.screen.assetlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.scalablesolutions.cryptoapp.R
import com.scalablesolutions.cryptoapp.presentation.viewmodel.AssetsListViewModel

@Composable
fun AssetsListScreen(
    navController: NavController,
    viewModel: AssetsListViewModel = hiltViewModel()
) {

    val lazyGameItems = viewModel.state.value.assets?.collectAsLazyPagingItems()
    val updatePriceState = viewModel.updatePriceState.value

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(lazyGameItems?.itemCount ?: 0) { index ->
                lazyGameItems?.get(index)?.let {
                    AssetListItem(
                        asset = it,
                        onItemClick = {

                        }
                    )
                }
            }
        }

        updatePriceState.assets.forEach { updatedAsset ->
            val itemNeedUpdate =
                lazyGameItems?.itemSnapshotList?.firstOrNull { updatedAsset.symbol == it?.symbol }
            if (itemNeedUpdate != null) {
                itemNeedUpdate.priceUsd = updatedAsset.priceUsd
            }
        }


        if (lazyGameItems?.loadState?.refresh is LoadState.Error || lazyGameItems?.loadState?.refresh is LoadState.Error) {
            Text(
                text = stringResource(R.string.asset_list_load_error),
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(lazyGameItems?.loadState?.refresh is LoadState.Loading || lazyGameItems?.loadState?.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}