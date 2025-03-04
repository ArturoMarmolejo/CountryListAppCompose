package com.arturomarmolejo.countrylistappcompose.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arturomarmolejo.countrylistappcompose.data.model.CountryAPIResponseItem
import com.arturomarmolejo.countrylistappcompose.ui.theme.CountryListAppComposeTheme

@Composable
fun CountryItem(
    country: CountryAPIResponseItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.background)
                .padding(16.dp).fillMaxWidth()
        ) {
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    text = "${country.name}, ${country.region}"
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    color = MaterialTheme.colorScheme.primary,
                    text = "${country.code}"
                )
            }
            Text(
                color = MaterialTheme.colorScheme.primary,
                text = "${country.capital}"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountryItemPreview() {
    CountryListAppComposeTheme {
        CountryItem(
            country = CountryAPIResponseItem(
                capital = "Buenos Aires",
                code = "AR",
                name = "Argentina",
            )
        )
    }
}
