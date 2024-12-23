package com.plcoding.cryptotracker.crypto.presentation.coinlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.plcoding.cryptotracker.crypto.presentation.coinlist.components.CoinListItem
import com.plcoding.cryptotracker.crypto.presentation.coinlist.components.previewCoinUi
import com.plcoding.cryptotracker.crypto.presentation.coinlist.components.toCoinUi
import com.plcoding.cryptotracker.ui.theme.AppTheme

@Composable
fun CoinListScreen(state: CoinListState, modifier: Modifier = Modifier) {
    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.coins) { coinUi ->
                CoinListItem(coinUi = coinUi, onClick = { }, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewCoinListScreen(modifier: Modifier = Modifier) {
    AppTheme {
        CoinListScreen(
            state = CoinListState(
                coins = (1..10).map {
                    previewCoinUi.toCoinUi()
                },
                isLoading = false,
                selectedCoin = null

            ),
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onBackground)
        )
    }
}