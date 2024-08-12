package com.example.hackathon

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONArray
import java.net.URISyntaxException

class WebSocketViewModel {

    private lateinit var mSocket: Socket

    private val initial_grid = GameRequest(
        requestGameState = List(12) {
            List(8) { "#FFFFFF" }
        }
    )

    var gameState by mutableStateOf(
        GameRequest(
            requestGameState = initial_grid.requestGameState
        )
    )

    init {
        try {
            mSocket = IO.socket("http://10.0.2.2:3000")
        } catch (e: URISyntaxException) {
            Log.e("Socket.IO", "Connection failed: ${e.message}")
        }
    }

    fun connectSocket() {
        mSocket.connect()
        mSocket.on(Socket.EVENT_CONNECT) {
            Log.d("Socket.IO", "Connected to server")
            mSocket.emit("message", "Hello from Android!")
        }

        mSocket.on("counter") { args ->
            val dataclass: MutableList<MutableList<String>> = MutableList(12) {
                MutableList(8) { "" }
            }

            val jsonArray = args[0] as JSONArray

            for (i in 0 until jsonArray.length()) {
                val innerJsonArray = jsonArray.getJSONArray(i)
                for (j in 0 until innerJsonArray.length()) {
                    dataclass[i][j] = innerJsonArray.getString(j)
                }
            }

            gameState = GameRequest(requestGameState = dataclass)
            println(gameState.requestGameState)
        }

        mSocket.on(Socket.EVENT_DISCONNECT) {
            Log.d("Socket.IO", "Disconnected from server")
        }
    }

    fun getGameState() {
        mSocket.on("counter") { args ->
            Log.d("Socket.IO", args[0]::class.toString())
        }

    }


    fun sendMessage(rowInt: Int,columnInt: Int,color : String) {
        mSocket.emit("rowInt",rowInt)
        mSocket.emit("columnInt",columnInt)
        mSocket.emit("color",color)

    }

}

