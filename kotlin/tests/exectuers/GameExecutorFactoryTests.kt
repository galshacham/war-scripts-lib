package tests.exectuers

import exceptions.NoExecutorException
import exceutors.GameExecutorFactory
import exceutors.GameExecutorInterface
import exceutors.PYTHON_EXECUTOR_PATH
import org.junit.Test
import kotlin.test.assertEquals

class GameExecutorFactoryTests {

    @Test(expected = NoExecutorException::class)
    fun whenGameExecutorFactoryCreatesNoneExistingExecutor_shouldThrowNoExecutorException() {
        val fact = GameExecutorFactory()

        fact.createExecutor("doesntExist")
    }

    @Test
    fun whenGameExecutorCreatesARealExecutor_shouldCreateTheExecutor() {
        val fact = GameExecutorFactory()

        val demoBotPath = "testResources/demoPythonCode.py"
        val pyExec = fact.createExecutor(demoBotPath)

        assertEquals(demoBotPath, pyExec.codeToExecutePath)
        assertEquals(PYTHON_EXECUTOR_PATH, pyExec.executorPath)
    }
}