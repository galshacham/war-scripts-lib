package executors

import Utils.Companion.getFileSuffix
import exceptions.NoExecutorException


class GameExecutorFactory {
    fun createExecutor(codePath: String): GameExecutorInterface {
        val suffix = getFileSuffix(codePath)
        return when (suffix) {
            "py" -> PythonExecutor(codePath)
            "js" -> JavascriptExecutor(codePath)
            else -> throw NoExecutorException("executor of type ($suffix) do not exist, it can be added on executors.properties file")
        }
    }
}