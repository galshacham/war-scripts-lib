import java.io.File

class Constants {
    companion object {
        // TODO: Needs to be changed
        val JS_RUNNER_PATH: String = File("src/main/resources/botRunners/js/src/javascriptRunner.js").absolutePath
        val PY_RUNNER_PATH: String = File("botRunners/pythonRunner.py").absolutePath

    }
}