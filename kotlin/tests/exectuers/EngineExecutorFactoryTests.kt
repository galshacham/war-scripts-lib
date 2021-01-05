package tests.exectuers

import exceptions.NoExecutorException
import executors.GameExecutorFactory
import executors.PYTHON_EXECUTOR_PATH
import org.junit.Test
import kotlin.test.assertEquals

class EngineExecutorFactoryTests {

    @Test(expected = NoExecutorException::class)
    fun whenGameExecutorFactoryCreatesNoneExistingExecutor_shouldThrowNoExecutorException() {
        val fact = GameExecutorFactory()

        fact.createExecutor("doesntExist")
    }

    @Test
    fun whenGameExecutorCreatesARealExecutor_shouldCreateTheExecutor() {
        val fact = GameExecutorFactory()

        val demoBotPath = "/demoPythonCode.py"
        val pyExec = fact.createExecutor(demoBotPath)

        assertEquals(demoBotPath, pyExec.codeToExecutePath)
        assertEquals(PYTHON_EXECUTOR_PATH, pyExec.executorPath)
    }
}