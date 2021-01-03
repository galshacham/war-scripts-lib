package exceutors

import Utils.Companion.getFileSuffix
import exceptions.NoExecutorException

val PYTHON_EXECUTOR_PATH = "HELLO WORLD"

class GameExecutorFactory {
    fun createExecutor(codePath: String): GameExecutorInterface {
        val suffix = getFileSuffix(codePath)
        return when (suffix) {
            "py" -> PythonExecutor(PYTHON_EXECUTOR_PATH, codePath)
            else -> throw NoExecutorException("executor of type ($suffix) do not exist, it can be added on executors.properties file")
        }
    }
}