package com.plcoding.cryptotracker.crypto.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.cryptotracker.crypto.domain.model.Coin
import com.plcoding.cryptotracker.crypto.presentation.models.CoinUi
import com.plcoding.cryptotracker.crypto.presentation.models.DisplayableNumber
import com.plcoding.cryptotracker.ui.theme.AppTheme
import com.plcoding.cryptotracker.util.getDrawableIdForCoin
import java.text.NumberFormat
import java.util.Locale

@Composable
fun CoinListItem(coinUi: CoinUi, onClick: () -> Unit, modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = coinUi.iconRes),
            contentDescription = coinUi.name,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(80.dp)
        )

        Column(modifier = Modifier.weight(1f)) {
            Text(text = coinUi.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)

        }
    }
}

@Preview
@Composable
@PreviewLightDark
private fun PrevComposable() {
    AppTheme {
        CoinListItem(
            coinUi = previewCoinUi.toCoinUi(),
            onClick = { /*TODO*/ },
            modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)
        )
    }

}

internal val previewCoinUi = Coin(
    id = "btc-bitcoin",
    rank = 1,
    name = "BTC",
    marketCapUsd = 123456789.0,
    priceUsd = 12345.0,
    changePercent24Hr = 123.0
)

fun Coin.toCoinUi(): CoinUi {
    return CoinUi(
        id = id,
        rank = rank,
        name = name,
        marketCapUsd = priceUsd.toDisplayableNumber(),
        priceUsd = priceUsd.toDisplayableNumber(),
        changePercent24Hr = changePercent24Hr.toDisplayableNumber(),
        iconRes = getDrawableIdForCoin(symbol = name)
    )
}

fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2

    }
    return DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}