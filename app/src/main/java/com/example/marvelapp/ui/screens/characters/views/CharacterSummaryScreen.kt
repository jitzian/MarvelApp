package com.example.marvelapp.ui.screens.characters.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberImagePainter
import com.example.marvelapp.R
import com.example.marvelapp.constants.GlobalConstants
import com.example.marvelapp.data.remote.model.ApiCharacter
import com.example.marvelapp.data.remote.model.asString

@Composable
fun <T> CharacterSummaryScreen(data: T?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.dimen_16_dp)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8_dp))
    ) {
        Image(
            painter = rememberImagePainter(
                data = (data as? ApiCharacter)?.thumbnail?.asString()
                    ?: GlobalConstants.imageTestUrl
            ),
            contentDescription = "",
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.dimen_96_dp))
                .height(dimensionResource(id = R.dimen.dimen_96_dp))
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.dimen_8_dp)))
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8_dp))
        ) {
            Text(
                text = (data as? ApiCharacter)?.name
                    ?: stringResource(id = R.string.no_data_available_TEXT),
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.fillMaxWidth()
            )
            (data as? ApiCharacter)?.description?.ifEmpty {
                stringResource(id = R.string.no_data_available_TEXT)
            }?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}