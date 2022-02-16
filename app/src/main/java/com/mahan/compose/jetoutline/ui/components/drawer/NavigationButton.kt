package com.mahan.compose.jetoutline.ui.components.drawer

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mahan.compose.jetoutline.R
import com.mahan.compose.jetoutline.util.getCorrectIconId

@Composable
fun NavigationButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    text: String,
) {
    val color = if (isSelected)
        MaterialTheme.colors.secondary
    else
        MaterialTheme.colors.onBackground

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = if (isSelected) MaterialTheme.colors.surface else Color.Transparent
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = getCorrectIconId(text)),
                contentDescription = "Servers",
                tint = color,
                modifier = Modifier
                    .size(20.dp)
                    .weight(15f)
            )

            Text(
                text = text,
                fontSize = MaterialTheme.typography.subtitle2.fontSize,
                color = color,
                modifier = Modifier.weight(80f)
            )
        }
    }
}

@Preview
@Composable
fun NavigationButtonPreview() {
    NavigationButton(isSelected = true, text = "About")
}