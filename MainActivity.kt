package com.example.hackathon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val webSocketViewModel = WebSocketViewModel()
        CoroutineScope(Dispatchers.IO).launch {
            webSocketViewModel.connectSocket()
        }
        setContent {
            Grid(color = webSocketViewModel.gameState,socket = webSocketViewModel )
            ColorPallete()
        }
    }
}





