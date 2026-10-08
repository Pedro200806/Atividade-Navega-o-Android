package com.PedroHenrique.navegaoandroid.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.PedroHenrique.navegaoandroid.ui.theme.screens.HomeScreen
import com.PedroHenrique.navegaoandroid.ui.theme.screens.LoginScreen
import com.PedroHenrique.navegaoandroid.ui.theme.screens.MovieDetailScreen
import com.PedroHenrique.navegaoandroid.ui.theme.screens.MoviesListScreen
import com.PedroHenrique.navegaoandroid.ui.theme.screens.ProfileScreen
import com.PedroHenrique.navegaoandroid.ui.theme.screens.SplashScreen
import com.PedroHenrique.navegaoandroid.viewmodel.AppViewModel

@Composable
fun AppNavigation() {
    // ViewModel único partilhado entre todas as telas da aplicação
    val sharedViewModel: AppViewModel = viewModel()
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = Route.Splash
    ) {
        // Splash Screen
        composable<Route.Splash> {
            SplashScreen(
                onSplashFinished = {
                    // Decisão condicional externa à Splash
                    val destination = if (sharedViewModel.isLoggedIn) {
                        Route.MainHost
                    } else {
                        Route.Login
                    }
                    rootNavController.navigate(destination) {
                        popUpTo<Route.Splash> { inclusive = true }
                    }
                }
            )
        }

        // Login Screen
        composable<Route.Login> {
            LoginScreen(
                viewModel = sharedViewModel,
                onLoginSuccess = {
                    rootNavController.navigate(Route.MainHost) {
                        popUpTo<Route.Login> { inclusive = true }
                    }
                }
            )
        }

        // Painel Principal com Bottom Navigation Persistente
        composable<Route.MainHost> {
            MainHostScreen(
                sharedViewModel = sharedViewModel,
                onLogout = {
                    rootNavController.navigate(Route.Login) {
                        popUpTo<Route.MainHost> { inclusive = true }
                    }
                }
            )
        }
    }
}

@Composable
fun MainHostScreen(
    sharedViewModel: AppViewModel,
    onLogout: () -> Unit
) {
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                // Aba 1: Início
                NavigationBarItem(
                    selected = currentDestination?.route?.contains("Home") == true,
                    onClick = {
                        bottomNavController.navigate(Route.Home) {
                            popUpTo(bottomNavController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Ir para a aba Início"
                        )
                    },
                    label = { Text("Início") }
                )

                // Aba 2: Filmes
                NavigationBarItem(
                    selected = currentDestination?.route?.contains("Movies") == true,
                    onClick = {
                        bottomNavController.navigate(Route.Movies) {
                            popUpTo(bottomNavController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Movie,
                            contentDescription = "Ir para a aba Filmes"
                        )
                    },
                    label = { Text("Filmes") }
                )

                // Aba 3: Perfil
                NavigationBarItem(
                    selected = currentDestination?.route?.contains("Profile") == true,
                    onClick = {
                        bottomNavController.navigate(Route.Profile) {
                            popUpTo(bottomNavController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Ir para a aba Perfil"
                        )
                    },
                    label = { Text("Perfil") }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = Route.Home,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Route.Home> {
                HomeScreen(viewModel = sharedViewModel)
            }

            composable<Route.Movies> {
                MoviesListScreen(
                    viewModel = sharedViewModel,
                    onMovieClick = { id ->
                        bottomNavController.navigate(Route.MovieDetail(movieId = id))
                    }
                )
            }

            // Tela de detalhe com parâmetro type-safe lido através de toRoute()
            composable<Route.MovieDetail> { backStackEntry ->
                val detailRoute: Route.MovieDetail = backStackEntry.toRoute()
                MovieDetailScreen(
                    movieId = detailRoute.movieId,
                    viewModel = sharedViewModel,
                    onBackClick = { bottomNavController.popBackStack() }
                )
            }

            composable<Route.Profile> {
                ProfileScreen(
                    viewModel = sharedViewModel,
                    onLogout = onLogout
                )
            }
        }
    }
}