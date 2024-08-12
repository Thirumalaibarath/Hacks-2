package com.example.hackathon

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import org.json.JSONArray
import java.net.URISyntaxException

class WebSocketViewModel:ViewModel() {

    private lateinit var mSocket: Socket

    private val initial_grid = GameRequest(
        requestGameState = listOf(
            listOf("#FF5733", "#FF5733", "#FF5733"),
            listOf("#FF5733", "#FF5733", "#FF5733"),
            listOf("#FF5733", "#FF5733", "#FF5733")
        )
    )

    var gameState: GameRequest = initial_grid

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

            val intArray = arrayOf(1, 2, 3, 4, 5)

            val jsonArray = JSONArray(intArray)

            mSocket.emit("message", jsonArray)
        }

        mSocket.on("counter") { args ->
            val jsonArray = args[0] as JSONArray

            for (i in 0 until jsonArray.length()) {
                val innerJsonArray = jsonArray.getJSONArray(i)
                val innerList = mutableListOf<String>()
                for (j in 0 until innerJsonArray.length()) {
                    innerList.add(innerJsonArray.getString(j))
                }
                gameState.requestGameState.
            }

            gameState = args[0] as GameRequest
            Log.d("Socket.IO", args[0].toString())
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


    fun sendMessage() {

        val intArray = arrayOf(1, 2, 3, 4, 5)
        val jsonArray = JSONArray(intArray)

        mSocket.emit("message", jsonArray)

    }


    fun disconnectSocket() {
        mSocket.disconnect()
    }
}

