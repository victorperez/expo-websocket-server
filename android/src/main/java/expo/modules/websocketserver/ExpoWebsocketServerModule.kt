package expo.modules.websocketserver

import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import expo.modules.websocketserver.SimpleWebSocketServer
import java.net.InetSocketAddress
import android.util.Log

class ExpoWebsocketServerModule : Module() {
  private var server: SimpleWebSocketServer? = null

  override fun definition() = ModuleDefinition {
    Name("ExpoWebsocketServer")

    Function("start") { portArg: Int? ->
      val port = portArg ?: 8887

      val address = InetSocketAddress(port)
      if (server == null) {
        server = SimpleWebSocketServer(address)
        try {
          server?.start()
        } catch (e: Exception) {
          Log.e("ExpoWebSocketServer", "Failed to start server", e)
        }
      } else {
        Log.i("ExpoWebSocketServer", "Server already running")
      }
    }

    Function("stop") {
      try {
        server?.stop()
        server = null
      } catch (e: Exception) {
        Log.e("ExpoWebSocketServer", "Failed to stop server", e)
      }
    }

    Function("isRunning") {
      server?.getIsRunning()
    }

    Function("getLocalIpAddress") {
      server?.getLocalIpAddress()
    }
  }
}
