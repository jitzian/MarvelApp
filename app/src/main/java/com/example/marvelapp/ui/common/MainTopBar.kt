package com.example.marvelapp.ui.common

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun MainTopBar(
    showBackButton: Boolean = false,
    barTitle: String? = null,
    onBackClick: (() -> Unit)? = null
) {
    if (showBackButton && onBackClick != null) {
        TopAppBar(
            title = {
                Text(text = barTitle?.takeIf { it.isNotEmpty() } ?: "")
            },
            navigationIcon = {
                ArrowBackIcon(onBackClick = onBackClick)
            }
        )
    } else {
        TopAppBar(
            title = { Text(text = barTitle?.takeIf { it.isNotEmpty() } ?: "") }
        )
    }
}