package com.mahan.compose.jetoutline

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mahan.compose.jetoutline.navigation.AppNavigation
import com.mahan.compose.jetoutline.ui.screens.home.HomeScreenViewModel
import com.mahan.compose.jetoutline.ui.theme.JetOutlineTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeScreenViewModel: HomeScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetOutline {
                AppNavigation(homeScreenViewModel = homeScreenViewModel)
            }
        }
    }
}

@Composable
fun JetOutline(content: @Composable () -> Unit) {
    JetOutlineTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.surface
        ) {
            content()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetOutlineTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.surface
        ) {}
    }
}