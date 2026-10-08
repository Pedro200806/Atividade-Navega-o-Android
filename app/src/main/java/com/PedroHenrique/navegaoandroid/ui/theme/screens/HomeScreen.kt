package com.PedroHenrique.navegaoandroid.ui.theme.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.PedroHenrique.navegaoandroid.viewmodel.AppViewModel

@Composable
fun HomeScreen(viewModel: AppViewModel) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Destaques do Dia",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}