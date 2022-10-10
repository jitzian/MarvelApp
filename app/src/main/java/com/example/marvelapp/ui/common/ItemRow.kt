package com.example.marvelapp.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.marvelapp.R
import com.example.marvelapp.data.remote.model.ApiCharacter

@Composable
fun <T> ItemRow(data: T) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.dimen_16_dp),
                vertical = dimensionResource(
                    id = R.dimen.dimen_8_dp
                )
            ),
        elevation = dimensionResource(id = R.dimen.dimen_4_dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8_dp))
        ) {
            Image(
                painter = rememberImagePainter(data = "https://cdn4.iconfinder.com/data/icons/bettericons/354/github-512.png"),
                contentDescription = "",
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
                    .padding(
                        start = dimensionResource(id = R.dimen.dimen_8_dp),
                        top = dimensionResource(id = R.dimen.dimen_8_dp),
                        bottom = dimensionResource(id = R.dimen.dimen_8_dp)
                    )
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = (data as ApiCharacter).name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = dimensionResource(id = R.dimen.dimen_8_dp),
                            top = dimensionResource(id = R.dimen.dimen_8_dp),
                            end = dimensionResource(id = R.dimen.dimen_8_dp)
                        ),
                    style = MaterialTheme.typography.body1
                )

                Text(
                    text = (data as ApiCharacter).description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = dimensionResource(id = R.dimen.dimen_8_dp),
                            top = dimensionResource(id = R.dimen.dimen_8_dp),
                            end = dimensionResource(id = R.dimen.dimen_8_dp),
                            bottom = dimensionResource(id = R.dimen.dimen_8_dp)
                        ),
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}