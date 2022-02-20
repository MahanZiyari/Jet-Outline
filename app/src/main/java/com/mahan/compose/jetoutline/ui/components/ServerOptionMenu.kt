package com.mahan.compose.jetoutline.ui.components

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun OptionsMenu(
    expanded: Boolean,
    onRenameClicked: () -> Unit,
    onForgetClicked: () -> Unit,
    onDismissRequest: () -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {
        DropdownMenuItem(
            onClick = {
                onRenameClicked()
                onDismissRequest()
            }
        ) {
            Text(text = "Rename")
        }

        DropdownMenuItem(
            onClick = {
                onForgetClicked()
                onDismissRequest()
            }
        ) {
            Text(text = "Forget")
        }
    }
}