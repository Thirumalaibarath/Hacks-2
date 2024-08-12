package com.example.hackathon

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hackathon.ui.theme.HackathonTheme
import io.socket.client.Socket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val webSocketViewModel = WebSocketViewModel()
        CoroutineScope(Dispatchers.IO).launch {
            webSocketViewModel.connectSocket()
        }

        val color = GameRequest(
            requestGameState = listOf(
                listOf("#FF5733", "#FF5733", "#FF5733"),
                listOf("#FF5733", "#FF5733", "#FF5733"),
                listOf("#FF5733", "#FF5733", "#FF5733")
            )
        )

        setContent {
//            webSocketViewModel.gameState?.let { Grid(color = it) }
            Grid(color = webSocketViewModel.gameState)
//            Trail(socket = webSocketViewModel)
            Log.d("value", webSocketViewModel.gameState.requestGameState.toString())

        }
    }
}

@Composable
fun Trail(socket: WebSocketViewModel)
{
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Button(
            onClick = {

            }
        )
        {
            Text(text = "click me")
        }
    }

}



