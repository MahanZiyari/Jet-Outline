package com.mahan.compose.jetoutline.ui.components.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mahan.compose.jetoutline.R

@Composable
fun NavigationDrawer(
    navController: NavHostController
) {
    Column(modifier = Modifier.background(MaterialTheme.colors.background)) {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(20f),
            color = MaterialTheme.colors.primary
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_outline_logoasset_1),
                    contentDescription = "Outline Logo",
                    modifier = Modifier.size(100.dp)
                )
            }
        }

        val navigationButtons =
            arrayListOf("Servers", "Submit Feedback", "About", "Help", "Change Language")

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.weight(40f)
        ) {
            navigationButtons.forEach {
                if (it == "Servers") {
                    NavigationButton(
                        modifier = Modifier
                            .height(60.dp)
                            .clickable { },
                        isSelected = true,
                        text = it
                    )
                } else {
                    NavigationButton(
                        modifier = Modifier
                            .height(60.dp)
                            .clickable { },
                        isSelected = false,
                        text = it
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Divider()

        val otherOptions =
            arrayListOf("Privacy", "Data Collection", "Terms", "Licenses")

        Column(
            modifier = Modifier.weight(40f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            otherOptions.forEach {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.background)
                        .height(40.dp)
                        .padding(vertical = 6.dp, horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(text = it, modifier = Modifier.alpha(ContentAlpha.medium))
                }
            }
        }

    }
}

@Preview
@Composable
fun NavigationDrawerPreview() {
    NavigationDrawer(navController = rememberNavController())
}