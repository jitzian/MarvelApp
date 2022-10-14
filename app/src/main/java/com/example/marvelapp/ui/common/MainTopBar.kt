package com.example.marvelapp.ui.common

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun MainTopBar(showBackButton: Boolean = false) {
    if (showBackButton) {
        TopAppBar(
            title = {
                Text(text = "This is the title")
            },
            navigationIcon = {
                ArrowBackIcon {
                    //TODO: To be implemented (action listener button)
                }
            }
        )
    } else {
        TopAppBar(
            title = { Text(text = "This is the title") }
        )
    }
}