package executors

import exceptions.RuntimeException
import main.GameJsonParser
import main.objects.actions.Action
import java.io.File


val STARTING_MARK = "___start___"

interface GameExecutorInterface {
    val executorPath: String
    val codeToExecutePath: String

    fun callExecutor(gameState: String, jsonParser: GameJsonParser, side: Int): List<Action> {
        val gameJsonWithoutSpaces = gameState.removeWhitespaces()

        val command = "$executorPath ${File(codeToExecutePath).absolutePath} $gameJsonWithoutSpaces $side"
        val process = Runtime.getRuntime().exec(command)

        val wholeOutput = process.inputStream.readBytes().toString(Charsets.UTF_8).removeWhitespaces()
        val errorOutput = process.errorStream.readBytes().toString(Charsets.UTF_8)

        print(wholeOutput)
        print(errorOutput)

        if (errorOutput.length != 0) {
            throw RuntimeException("Compilation error of bot, stacktrace presented here: $errorOutput")
        }


        val actionsStartIndex = wholeOutput.lastIndexOf(STARTING_MARK)

        val actionsJson = wholeOutput.removeRange(0, actionsStartIndex + STARTING_MARK.length)

        return jsonParser.parseToActions(actionsJson)
    }

}

private fun String.removeWhitespaces(): String = this.replace("\\s".toRegex(), "")
