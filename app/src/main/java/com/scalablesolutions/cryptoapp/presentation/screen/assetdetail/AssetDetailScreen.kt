package com.scalablesolutions.cryptoapp.presentation.screen.assetdetail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.scalablesolutions.cryptoapp.R
import com.scalablesolutions.cryptoapp.presentation.ui.theme.ColorPrimary
import com.scalablesolutions.cryptoapp.presentation.viewmodel.AssetDetailViewModel

@Composable
fun AssetDetailScreen(
    viewModel: AssetDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val context = LocalContext.current

    DisposableEffect(key1 = viewModel) {
        viewModel.onStart()
        onDispose { viewModel.onStop() }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        state.asset?.let { asset ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Text(
                        text = asset.symbol,
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier
                            .graphicsLayer {
                                shadowElevation = 8.dp.toPx()
                                shape = RoundedCornerShape(8.dp)
                                clip = true
                            }
                            .requiredWidth(56.dp)
                            .background(color = ColorPrimary)
                            .padding(4.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = asset.name,
                            style = MaterialTheme.typography.body1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.width(32.dp))
                        Text(
                            text = "${String.format("%.2f", asset.priceUsd)} $",
                            style = MaterialTheme.typography.body1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = asset.tagline ?: "",
                        style = MaterialTheme.typography.body1
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                    if (asset.officialLinks.isNotEmpty()) {
                        Text(
                            text = stringResource(R.string.official_links_title),
                            style = MaterialTheme.typography.h3
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }
                items(asset.officialLinks) { link ->
                    LinkListItem(
                        link = link,
                        modifier = Modifier
                            .fillMaxWidth(),
                        onItemClick = { officialLink ->
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(officialLink.link)
                                )
                            )
                        }
                    )
                    Divider()
                }
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
    }
}