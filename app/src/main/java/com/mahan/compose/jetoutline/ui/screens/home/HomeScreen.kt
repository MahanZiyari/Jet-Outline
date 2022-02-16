package com.mahan.compose.jetoutline.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mahan.compose.jetoutline.ui.components.appbar.HomeScreenAppBar
import com.mahan.compose.jetoutline.ui.components.drawer.NavigationDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeScreenViewModel
) {
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    // UI
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            HomeScreenAppBar(
                onAddClicked = {
                    scope.launch {
                        sheetState.show()
                    }
                },
                onMenuClicked = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerContent = {
            NavigationDrawer(navController = navController)
        }
    ) {
        Content(sheetState = sheetState, coroutineScope = scope, viewModel = viewModel)
    }
}

@ExperimentalMaterialApi
@Composable
private fun Content(
    sheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope,
    viewModel: HomeScreenViewModel
) {
    val accessKey by viewModel.accessKey
    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetState,
        sheetBackgroundColor = MaterialTheme.colors.background,
        sheetContent = {
            SheetContent(
                accessKey = accessKey,
                onAccessKeyChange = { viewModel.onAccessKeyChange(it) })
        }
    ) {
        // Screen Content Goes Here
    }
}

@Composable
fun SheetContent(
    accessKey: String,
    onAccessKeyChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Add access key",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Text(
                text = "copy and paste an access key to add a server",
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .alpha(ContentAlpha.medium),
            )

            OutlinedTextField(
                value = accessKey,
                onValueChange = { newValue -> onAccessKeyChange(newValue) },
                label = { Text(text = "Access key") },
                placeholder = { Text(text = "ss://access-key") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
        }

        if (accessKey.isEmpty() || accessKey.isBlank()) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colors.surface
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    Text(
                        text = "Don't have an access key?",
                        fontSize = MaterialTheme.typography.subtitle2.fontSize,
                        color = MaterialTheme.colors.onSurface
                    )

                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onSurface,
                                    fontSize = MaterialTheme.typography.subtitle2.fontSize
                                )
                            ) {
                                append("Create your own at ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.secondary,
                                    fontSize = MaterialTheme.typography.subtitle2.fontSize
                                )
                            ) {
                                append("our website")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onSurface,
                                    fontSize = MaterialTheme.typography.subtitle2.fontSize
                                )
                            ) {
                                append(".")
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun BottomSheetPreview() {
    SheetContent(accessKey = "", onAccessKeyChange = {})
}
