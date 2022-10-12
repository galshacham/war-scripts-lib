import java.io.File

class Constants {
    companion object {
        // TODO: Needs to be changed
        val JS_RUNNER_PATH: String = File("runner/src/main/resources/apis/js/src/typescriptRunner.ts").absolutePath
        val TS_RUNNER_PATH: String = File("runner/src/main/resources/apis/ts/src/typescriptRunner.ts").absolutePath
        val PY_RUNNER_PATH: String = File("botRunners/pythonRunner.py").absolutePath

    }
}