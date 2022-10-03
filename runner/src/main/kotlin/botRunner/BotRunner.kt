package botRunner

import exceptions.BotRuntimeException
import java.io.BufferedReader
import java.io.InputStreamReader

class BotRunner(botAbsolutePath: String, player: Int, runtime: Runtime, execString: String) : IBotRunner {
    private var process: Process

    init {
        val command = "$execString $botAbsolutePath $player"
        this.process = runtime.exec(command)
    }

    override fun doTurn(currentGameStateJson: String): String {
        val inputStream = BufferedReader(InputStreamReader(process.inputStream))
        process.outputStream.write(currentGameStateJson.toByteArray())
        process.outputStream.flush()

        val newGameStateJson = inputStream.readLine()

        if (newGameStateJson == null) {
            val errorStream = BufferedReader(InputStreamReader(process.errorStream))
            val errorOutput = errorStream.readText()

            throw BotRuntimeException("Compilation error of bot, stacktrace presented here:\n$errorOutput")
        }

        return newGameStateJson
    }
}
