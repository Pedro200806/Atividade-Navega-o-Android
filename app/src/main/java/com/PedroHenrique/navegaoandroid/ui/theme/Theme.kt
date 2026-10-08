package com.PedroHenrique.navegaoandroid.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Paleta de cores própria (nenhum ecrã utiliza cores hardcoded)
private val CustomLightColorScheme = lightColorScheme(
    primary = Color(0xFFE50914),       // Vermelho Cinema
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFDAD6),
    secondary = Color(0xFF1F2937),     // Cinzento Escuro
    onSecondary = Color(0xFFFFFFFF),
    background = Color(0xFFF9FAFB),    // Fundo Claro
    onBackground = Color(0xFF111827),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1F2937),
    surfaceVariant = Color(0xFFF3F4F6),
    onSurfaceVariant = Color(0xFF4B5563)
)

@Composable
fun CinemaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = CustomLightColorScheme,
        content = content
    )
}