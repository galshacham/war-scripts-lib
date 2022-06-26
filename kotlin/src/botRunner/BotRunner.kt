package botRunner

import exceptions.BotRuntimeException
import java.io.BufferedReader
import java.io.InputStreamReader

class BotRunner(botAbsolutePath: String, side: Int, runtime: Runtime, execString: String) {
    private var process: Process

    init {
        val command = "$execString $botAbsolutePath $side"
        this.process = runtime.exec(command)
    }

    fun doTurn(currentGameStateJson: String): String {
        val inputStream = BufferedReader(InputStreamReader(process.inputStream))
        process.outputStream.write(currentGameStateJson.removeWhitespaces().toByteArray())
        process.outputStream.flush()

        val newGameStateJson = inputStream.readLine()

        if (newGameStateJson == null) {
            val errorStream = BufferedReader(InputStreamReader(process.errorStream))
            val errorOutput = errorStream.readLine()
            print(errorOutput)
            // TODO: handle this better
            throw BotRuntimeException("Compilation error of bot, stacktrace presented here: $errorOutput")
        }

        return newGameStateJson
    }

}

fun String.removeWhitespaces(): String = this.replace("\\s".toRegex(), "")

