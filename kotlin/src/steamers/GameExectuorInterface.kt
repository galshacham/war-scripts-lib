package steamers

import main.GameJsonParser
import main.objects.actions.Action
import java.io.BufferedReader
import java.io.InputStreamReader


val STARTING_MARK = "___start___"

interface GameStreamerInterface {
    val streamerPath: String
    val side: Int

    fun getProcess(): Process

    fun callStreamer(gameState: String, jsonParser: GameJsonParser, side: Int): List<Action> {
        getProcess().outputStream.write(gameState.removeWhitespaces().toByteArray())
        getProcess().outputStream.flush()

        val inputStream = BufferedReader(InputStreamReader(getProcess().inputStream))
        while (inputStream.readLine()?.also { it ->
                    println(it)
                    getProcess().outputStream.write(gameState.toByteArray())
                    getProcess().outputStream.flush()
                } != null) {
        }

        val errorOutput = getProcess().errorStream.readBytes().toString(Charsets.UTF_8)

        print(errorOutput)

        if (errorOutput.isNotEmpty()) {
            throw RuntimeException("Compilation error of bot, stacktrace presented here: $errorOutput")
        }


//        val actionsStartIndex = wholeOutput.lastIndexOf(STARTING_MARK)

//        val actionsJson = wholeOutput.removeRange(0, actionsStartIndex + STARTING_MARK.length)

//        return jsonParser.parseToActions(actionsJson)
        return listOf()
    }

}

fun String.removeWhitespaces(): String = this.replace("\\s".toRegex(), "")
