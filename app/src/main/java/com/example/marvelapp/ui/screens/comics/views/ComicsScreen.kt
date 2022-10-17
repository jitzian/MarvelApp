package com.example.marvelapp.ui.screens.comics.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ComicsScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(text = "This is a a test", modifier = Modifier.fillMaxWidth())
    }
}