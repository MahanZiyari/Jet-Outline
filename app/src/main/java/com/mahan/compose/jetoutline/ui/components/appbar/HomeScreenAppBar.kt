package com.mahan.compose.jetoutline.ui.components.appbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreenAppBar(
    onAddClicked: () -> Unit,
    onMenuClicked: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 4.dp,
        // contentColor = MaterialTheme.colors.onPrimary
    ) {
        IconButton(
            onClick = onMenuClicked,
            modifier = Modifier.weight(10f)
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Drawer Icon",
                modifier = Modifier.size(30.dp),
                tint = MaterialTheme.colors.onPrimary
            )
        }

        Text(
            text = "Jet Outline",
            style = TextStyle(
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.weight(60f),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onPrimary
        )

        IconButton(
            onClick = onAddClicked,
            modifier = Modifier.weight(10f)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Server",
                modifier = Modifier.size(30.dp),
                tint = MaterialTheme.colors.onPrimary
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenAppBarPreview() {
    HomeScreenAppBar(
        onAddClicked = {},
        onMenuClicked = {}
    )
}