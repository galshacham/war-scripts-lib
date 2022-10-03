package botRunner

import Utils.Companion.getFileSuffix
import exceptions.UnsupportedBotRunnerException


class BotRunnerFactory(private val runtime: Runtime) {
    // TODO: Change it to env variable
    private val JS_EXEC_STRING = "node " + Constants.JS_RUNNER_PATH
    private val PY_EXEC_STRING = "py " + Constants.PY_RUNNER_PATH

    fun createBotRunner(codePath: String, side: Int): IBotRunner {
        val suffix = getFileSuffix(codePath)

        return when (suffix) {
            "js" -> BotRunner(codePath, side, runtime, JS_EXEC_STRING)
            else -> throw UnsupportedBotRunnerException("botRunner of type ($suffix) do not exist, it can be added on streamers.properties file")
        }
    }
}