package executors

import java.io.File

const val JAVASCRIPT_INTERPRETER = "node "
val JAVASCRIPT_EXECUTOR = JAVASCRIPT_INTERPRETER + File("resources/callers/JavascriptCaller.js").absolutePath

class JavascriptExecutor(override val codeToExecutePath: String) : GameExecutorInterface {
    override val executorPath = JAVASCRIPT_EXECUTOR
}