package com.victorr.cryptoapp.presentation.screen.assetlist

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
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.victorr.cryptoapp.R
import com.victorr.cryptoapp.presentation.viewmodel.AssetsListViewModel

@Composable
fun AssetsListScreen(
    viewModel: AssetsListViewModel = hiltViewModel()
) {

    val lazyPagingItems = viewModel.state.value.assets?.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(lazyPagingItems?.itemCount ?: 0) { index ->
                lazyPagingItems?.get(index)?.let {
                    AssetListItem(
                        asset = it
                    )
                }
            }
        }

        if (lazyPagingItems?.loadState?.refresh is LoadState.Error || lazyPagingItems?.loadState?.refresh is LoadState.Error) {
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
        if (lazyPagingItems?.loadState?.refresh is LoadState.Loading || lazyPagingItems?.loadState?.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}