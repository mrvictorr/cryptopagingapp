package com.victorr.cryptoapp.presentation.screen.assetlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.victorr.cryptoapp.presentation.model.AssetPresentation
import com.victorr.cryptoapp.presentation.ui.theme.ColorPrimary

@Composable
fun AssetListItem(
    asset: AssetPresentation
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Row(horizontalArrangement = Arrangement.Start) {
            Text(
                text = asset.symbol,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                color = Color.Black,
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .graphicsLayer {
                        shadowElevation = 8.dp.toPx()
                        shape = RoundedCornerShape(8.dp)
                        clip = true
                    }
                    .requiredWidth(56.dp)
                    .background(color = ColorPrimary)
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = asset.name,
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                        .align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.width(32.dp))
        Text(
            text = "${String.format("%.2f", asset.priceUsd)} $",
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.CenterEnd)
        )
    }
}