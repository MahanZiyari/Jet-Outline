package com.mahan.compose.jetoutline.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahan.compose.jetoutline.R
import com.mahan.compose.jetoutline.data.models.Server

@Composable
fun ServerItem(
    server: Server,
    expanded: Boolean = false,
    onConnectRequest: (Server) -> Unit,
    onDisconnectRequest: (Server) -> Unit,
    renameText: String,
    onRenameTextChange: (String) -> Unit,
    onRename: () -> Unit,
    onForget: (Server) -> Unit
) {
    if (expanded) {
        ExpandedServerItem(
            server = server,
            onConnectRequest = onConnectRequest,
            onDisconnectRequest = onDisconnectRequest,
            onRename = onRename,
            onForget = onForget,
            renameText = renameText,
            onRenameTextChange = onRenameTextChange
        )
    } else {
        CollapsedServerItem(
            server = server,
            onConnectRequest = onConnectRequest,
            onDisconnectRequest = onDisconnectRequest,
            onRename = onRename,
            onForget = onForget,
            renameText = renameText,
            onRenameTextChange = onRenameTextChange
        )
    }
}

@Composable
private fun CollapsedServerItem(
    server: Server,
    onConnectRequest: (Server) -> Unit,
    onDisconnectRequest: (Server) -> Unit,
    onRename: () -> Unit,
    onForget: (Server) -> Unit,
    renameText: String,
    onRenameTextChange: (String) -> Unit
) {
    val circleColor by animateColorAsState(
        targetValue = if (server.connected) MaterialTheme.colors.secondary else MaterialTheme.colors.primary,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 8.dp,
        shape = MaterialTheme.shapes.large,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Canvas(
                    modifier = Modifier
                        .size(40.dp)
                        .weight(10f)
                ) {
                    drawCircle(
                        color = circleColor
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .weight(80f)
                        .padding(horizontal = 10.dp)
                ) {
                    Text(
                        text = server.name,
                        style = MaterialTheme.typography.h6,
                        color = Color.Black
                    )
                    Text(
                        modifier = Modifier
                            .alpha(ContentAlpha.medium),
                        text = "${server.ip}:${server.port}",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onBackground
                    )
                }

                IconButton(
                    onClick = { expanded = true },
                    modifier = Modifier.weight(10f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_kebab),
                        contentDescription = "More",
                        modifier = Modifier.size(30.dp)
                    )

                    OptionsMenu(
                        expanded = expanded,
                        onRenameClicked = { /*TODO*/ },
                        onForgetClicked = { onForget(server) },
                        onDismissRequest = { expanded = false }
                    )
                }
            }



            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                TextButton(
                    onClick = {
                        if (server.connected)
                            onDisconnectRequest(server)
                        else
                            onConnectRequest(server)
                    }
                ) {
                    Text(
                        text = if (server.connected) "Disconnect" else "Connect",
                        style = MaterialTheme.typography.button,
                        fontSize = 18.sp,
                        color = MaterialTheme.colors.secondary,
                        modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp)
                    )
                }
            }
        }
    }
}


@Composable
private fun ExpandedServerItem(
    server: Server,
    onConnectRequest: (Server) -> Unit,
    onDisconnectRequest: (Server) -> Unit,
    onRename: () -> Unit,
    onForget: (Server) -> Unit,
    renameText: String,
    onRenameTextChange: (String) -> Unit
) {
    val circleColor by animateColorAsState(
        targetValue = if (server.connected) MaterialTheme.colors.secondary else MaterialTheme.colors.primary,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    var expanded by remember {
        mutableStateOf(false)
    }

    var openDialog by remember {
        mutableStateOf(false)
    }

    RenameDialog(
        expanded = openDialog,
        title = "Rename",
        text = renameText,
        onTextChange = {
            onRenameTextChange(it)
        },
        onDismissRequest = { openDialog = false },
        onConfirm = {
            onRename()
        }
    )

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 8.dp,
        shape = MaterialTheme.shapes.large,
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)
            ) {

                // Server Info
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .weight(80f)
                        .padding(horizontal = 10.dp)
                ) {
                    Text(
                        text = server.name,
                        style = MaterialTheme.typography.h6,
                        color = Color.Black
                    )
                    Text(
                        modifier = Modifier
                            .alpha(ContentAlpha.medium),
                        text = "${server.ip}:${server.port}",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onBackground
                    )
                }

                IconButton(
                    onClick = { expanded = true },
                    modifier = Modifier.weight(10f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_kebab),
                        contentDescription = "More",
                        modifier = Modifier.size(30.dp)
                    )

                    OptionsMenu(
                        expanded = expanded,
                        onRenameClicked = { openDialog = true },
                        onForgetClicked = { onForget(server) },
                        onDismissRequest = { expanded = false }
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            // Connection Status
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Canvas(modifier = Modifier.size(200.dp)) {
                    drawCircle(color = circleColor)
                }
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    text = if (server.connected) "Connected" else "Disconnected",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface),
                contentAlignment = Alignment.CenterEnd
            ) {
                TextButton(
                    onClick = {
                        if (server.connected)
                            onDisconnectRequest(server)
                        else
                            onConnectRequest(server)
                    }
                ) {
                    Text(
                        text = if (server.connected) "Disconnect" else "Connect",
                        style = MaterialTheme.typography.button,
                        fontSize = 18.sp,
                        color = MaterialTheme.colors.secondary,
                        modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ExpandedServerItemPreview() {
    /*ExpandedServerItem(
        server = Server(),
        onConnectRequest = {},
        onDisconnectRequest = {},
        onRename = {},
        onForget = {},
    )*/
}

@Preview
@Composable
private fun CollapsedServerItemPreview() {
    /*CollapsedServerItem(
        server = Server(),
        onConnectRequest = {},
        onDisconnectRequest = {},
        onRename = {},
        onForget = {},
    )*/
}