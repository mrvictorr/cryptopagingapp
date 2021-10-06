package com.scalablesolutions.cryptoapp.presentation.screen.assetdetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.scalablesolutions.cryptoapp.presentation.model.OfficialLinkPresentation

@Composable
fun LinkListItem(
    link: OfficialLinkPresentation,
    modifier: Modifier = Modifier,
    onItemClick: (OfficialLinkPresentation) -> Unit
) {
    Column(
        modifier = modifier
            .clickable { onItemClick(link) },
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = link.name,
            style = MaterialTheme.typography.h4
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = link.link,
            style = MaterialTheme.typography.body2,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}