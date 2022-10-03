package botRunner

import Utils.Companion.getFileSuffix
import exceptions.UnsupportedBotRunnerException


class BotRunnerFactory(private val runtime: Runtime) {
    // TODO: Change it to env variable
    private val JS_EXEC_STRING = "node " + Constants.JS_RUNNER_PATH
    val PY_EXEC_STRING = "py " + Constants.PY_RUNNER_PATH

    fun createBotRunner(codePath: String, side: Int): BotRunner {
        val suffix = getFileSuffix(codePath)

        return when (suffix) {
//            "py" -> PythonStreamer(codePath)
            "js" -> BotRunner(codePath, side, runtime, JS_EXEC_STRING)
            else -> throw UnsupportedBotRunnerException("botRunner of type ($suffix) do not exist, it can be added on streamers.properties file")
        }
    }
}