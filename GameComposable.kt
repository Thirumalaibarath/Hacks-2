package com.example.hackathon

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Grid(color : GameRequest)
{
    val gameState  = color.requestGameState

    Log.d("yapp",gameState.toString())

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color.Yellow)
    {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (rowIndex in gameState.indices) {
                Row {
                    for (colIndex in gameState[rowIndex].indices) {
                        val cellValue = gameState[rowIndex][colIndex]

                        Cell(
                            color = cellValue,
                            onClick = { clickedValue ->
                            })
                    }
                }
            }
        }
    }
}

@Composable
fun Cell(
    color: String,
    onClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .size(70.dp)
            .padding(10.dp)
            .border(width = 4.dp,color = Color.Black)
            .clickable { onClick(color) }
            .background(color = hexToColor(color)),
    )
}

fun hexToColor(hex: String): Color {
    val colorString = if (hex.startsWith("#")) hex else "#$hex"
    return Color(android.graphics.Color.parseColor(colorString))
}

