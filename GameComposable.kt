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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

var chosenColor by mutableStateOf("")

@Composable
fun Grid(color : GameRequest,socket: WebSocketViewModel)
{
    val gameState  = color.requestGameState

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray)
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
                            rowIndex = rowIndex,
                            columnIndex = colIndex,
                            color = cellValue,
                            onClick = { row,column ->
                                Log.d("column",column.toString())
                                Log.d("row",row.toString())
                                socket.sendMessage(rowInt = row, columnInt = column,color = chosenColor)
                            })
                    }
                }
            }
        }
    }
}

@Composable
fun ColorPallete()
{
    Row(modifier = Modifier.fillMaxSize()
        .padding(0.dp,0.dp,0.dp,50.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom)
    {
        val colors = listOf("#FF5733", "#008000","#FFA500","#8A2BE2","#0000FF","#FFFFFF")
        for(i in colors.indices){
            Cell(
                rowIndex = 0,
                columnIndex = i,
                color = colors[i],
                onClick = { row,column ->
                    chosenColor = colors[column]
                })
        }


    }
}

@Composable
fun Cell(
    columnIndex : Int,
    rowIndex : Int,
    color: String,
    onClick: (Int,Int) -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .padding(2.dp)
            .border(width = 4.dp,color = Color.Black)
            .clickable { onClick(rowIndex,columnIndex) }
            .background(color = hexToColor(color)),
    )
}

fun hexToColor(hex: String): Color {
    val colorString = if (hex.startsWith("#")) hex else "#$hex"
    return Color(android.graphics.Color.parseColor(colorString))
}

