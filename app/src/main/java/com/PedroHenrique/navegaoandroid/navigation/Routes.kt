package com.PedroHenrique.navegaoandroid.navigation

import kotlinx.serialization.Serializable

// Rotas Type-Safe declaradas como sealed interface com @Serializable
sealed interface Route {
    @Serializable
    data object Splash : Route

    @Serializable
    data object Login : Route

    @Serializable
    data object MainHost : Route

    // Rotas das abas do Bottom Navigation
    @Serializable
    data object Home : Route

    @Serializable
    data object Movies : Route

    @Serializable
    data object Profile : Route

    // Rota de detalhe com parâmetro type-safe
    @Serializable
    data class MovieDetail(val movieId: Int) : Route
}