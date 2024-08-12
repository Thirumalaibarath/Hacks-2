package com.example.hackathon

import androidx.compose.ui.graphics.Color


data class GameResponse(
    val responseGameState : List<List<String>>,
)

data class GameRequest(
    val requestGameState : List<List<String>>,
)
