package steamers

import exceptions.BotRuntimeException
import main.GameJsonParser
import main.objects.actions.Action
import java.io.BufferedReader
import java.io.InputStreamReader


val STARTING_MARK = "___start___"

interface GameStreamerInterface {
    val streamerPath: String
    val side: Int

    fun getProcess(): Process

    fun callStreamer(gameState: String, jsonParser: GameJsonParser): List<Action> {
        val inputStream = BufferedReader(InputStreamReader(getProcess().inputStream))
        getProcess().outputStream.write(gameState.removeWhitespaces().toByteArray())
        getProcess().outputStream.flush()

        val actionsJson = inputStream.readLine()

        if (actionsJson == null) {
            val errorStream = BufferedReader(InputStreamReader(getProcess().errorStream))
            val errorOutput = errorStream.readLine()
            print(errorOutput)
            throw BotRuntimeException("Compilation error of bot, stacktrace presented here: $errorOutput")
        }

        return jsonParser.parseToActions(actionsJson)
    }

}

fun String.removeWhitespaces(): String = this.replace("\\s".toRegex(), "")
