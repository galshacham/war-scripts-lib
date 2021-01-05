package executors

val PYTHON_EXECUTOR_PATH = "HELLO WORLD"

class PythonExecutor(override val codeToExecutePath: String) : GameExecutorInterface {
    override val executorPath = PYTHON_EXECUTOR_PATH
}