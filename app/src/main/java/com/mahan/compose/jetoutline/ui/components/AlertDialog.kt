package com.mahan.compose.jetoutline.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun RenameDialog(
    expanded: Boolean,
    title: String,
    text: String,
    onTextChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit
) {
    if (!expanded) return
    AlertDialog(
        backgroundColor = MaterialTheme.colors.background,
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onBackground,
            )
        },
        text = {
            Box() {
                TextField(
                    value = text,
                    onValueChange = { onTextChange(it) },
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = MaterialTheme.colors.secondary,
                        backgroundColor = Color.Transparent
                    ),
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = MaterialTheme.typography.subtitle1.fontSize
                    )
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = "Cancel", color = MaterialTheme.colors.secondary)
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                    onDismissRequest()
                }
            ) {
                Text(text = "Save", color = MaterialTheme.colors.secondary)
            }
        }
    )
}