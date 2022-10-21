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
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.example.marvelapp.R
import com.example.marvelapp.constants.GlobalConstants

@Composable
@Preview(showBackground = true)
fun CharacterSummaryScreen() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.dimen_16_dp)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8_dp))
    ) {
        Image(
            painter = rememberImagePainter(data = GlobalConstants.imageTestUrl),
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
                text = "This is the name",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "This is the description",
                style = MaterialTheme.typography.caption,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}