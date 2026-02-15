package ar.edu.um

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import ar.edu.um.screens.MainNavigationScreen

@Composable
fun App() {
    MaterialTheme {
        Navigator(
            screen = MainNavigationScreen()
        )


    }
}