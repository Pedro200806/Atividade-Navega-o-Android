package com.PedroHenrique.navegaoandroid.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class Movie(
    val id: Int,
    val title: String,
    val genre: String,
    val rating: Double,
    val description: String
)

class AppViewModel : ViewModel() {
    var isLoggedIn by mutableStateOf(false)
        private set

    var moviesList by mutableStateOf<List<Movie>>(
        listOf(
            Movie(
                id = 1,
                title = "Interstellar",
                genre = "Ficção Científica",
                rating = 8.7,
                description = "Uma equipa de exploradores viaja através de um buraco de minhoca no espaço para garantir a sobrevivência da humanidade."
            ),
            Movie(
                id = 2,
                title = "Inception",
                genre = "Ação / Sci-Fi",
                rating = 8.8,
                description = "Um ladrão especializado em roubar segredos corporativos através da partilha de sonhos recebe uma missão quase impossível."
            ),
            Movie(
                id = 3,
                title = "The Dark Knight",
                genre = "Ação / Drama",
                rating = 9.0,
                description = "Quando a ameaça conhecida como Joker causa estragos em Gotham, o Batman deve aceitar um dos maiores testes psicológicos e físicos."
            )
        )
    )
        private set

    fun login() {
        isLoggedIn = true
    }

    fun logout() {
        isLoggedIn = false
    }

    fun clearMovies() {
        moviesList = emptyList()
    }

    fun getMovieById(id: Int): Movie? {
        return moviesList.find { it.id == id }
    }
}