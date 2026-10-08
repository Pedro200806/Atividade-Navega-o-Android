package com.PedroHenrique.navegaoandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.PedroHenrique.navegaoandroid.navigation.AppNavigation
import com.PedroHenrique.navegaoandroid.ui.theme.CinemaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CinemaTheme {
                AppNavigation()
            }
        }
    }
}