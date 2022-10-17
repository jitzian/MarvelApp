package com.example.marvelapp.ui.common

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.marvelapp.R
import com.example.marvelapp.data.remote.model.ApiCharacter
import com.example.marvelapp.data.remote.model.asString

@Composable
fun <T> ItemRow(data: T, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.dimen_16_dp),
                vertical = dimensionResource(
                    id = R.dimen.dimen_8_dp
                )
            )
            .clickable {
                onItemClick.invoke()
            },
        elevation = dimensionResource(id = R.dimen.dimen_4_dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.dimen_8_dp)
            )
        ) {
            Image(
                painter = rememberImagePainter(data = (data as ApiCharacter).thumbnail.asString()),
                contentDescription = (data as ApiCharacter).name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(
                        all = dimensionResource(id = R.dimen.dimen_8_dp)
                    )
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .aspectRatio(1f)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = (data as ApiCharacter).name,
                    style = MaterialTheme.typography.body2,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(8.dp, 16.dp)
                        .weight(1f)
                )
                IconButton(
                    onClick = {
                        /*TODO*/
                        Log.e("ItemRow", "ItemRow: -- show details..!!")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = ""
                    )
                }
            }
        }
    }
}