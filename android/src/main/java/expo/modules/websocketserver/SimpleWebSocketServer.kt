package expo.modules.websocketserver

import android.util.Log
import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import org.json.JSONObject
import org.json.JSONException
import java.net.InetSocketAddress

class SimpleWebSocketServer(address: InetSocketAddress) : WebSocketServer(address) {
  var isRunning = false

  override fun onStart() {
    isRunning = true

    val ip = getLocalIpAddress()
    val port = address.port
    Log.i("ExpoWebSocketServer", "Server started at ws://$ip:$port")
    Log.i("ExpoWebSocketServer", "Server started successfully")
  }

  override fun onOpen(conn: WebSocket, handshake: ClientHandshake?) {
    Log.i("ExpoWebSocketServer", "Client connected: ${conn.remoteSocketAddress}")
  }

  override fun onClose(conn: WebSocket, code: Int, reason: String?, remote: Boolean) {
    Log.i("ExpoWebSocketServer", "Client disconnected: ${conn.remoteSocketAddress}")
  }

  override fun onMessage(conn: WebSocket, message: String) {
    Log.i("ExpoWebSocketServer", "Received message: $message")
    conn.send("$message")
  }

  override fun onError(conn: WebSocket?, ex: Exception?) {
    Log.e("ExpoWebSocketServer", "Error occurred", ex)
  }

  fun shutdown() {
    try {
      stop()
      isRunning = false
      Log.i("ExpoWebSocketServer", "Server shutdown")
    } catch (e: Exception) {
      Log.e("ExpoWebSocketServer", "Shutdown error", e)
    }
  }

  fun getIsRunning(): Boolean {
    Log.i("ExpoWebSocketServer", "getIsRunning: $isRunning")
    return isRunning
  }

  fun getLocalIpAddress(): String {
    try {
      val interfaces = java.net.NetworkInterface.getNetworkInterfaces()
      for (intf in interfaces) {
        val addrs = intf.inetAddresses
        for (addr in addrs) {
          if (!addr.isLoopbackAddress && addr is java.net.Inet4Address) {
            return addr.hostAddress
          }
        }
      }
    } catch (ex: Exception) {
      Log.e("WebSocketServer", "Failed to get IP", ex)
    }
    return "unknown"
  }
}
