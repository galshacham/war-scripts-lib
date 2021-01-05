package tests.exectuers

import exceptions.RuntimeException
import executors.JavascriptExecutor
import main.GameJsonParser
import main.objects.actions.ChangeSoldierTypeAction
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

class EngineExecutorInterfaceTests {
    @Test
    fun whenParsingJavascriptExecutorCallResult_shouldReturnActionsList() {
        val gameState = File("testResources/simpleGameState.json").readText()
        val executor = JavascriptExecutor("testResources/demoJsCode.js")
        val actions = executor.callExecutor(gameState, GameJsonParser())
        val expectedActions = listOf(ChangeSoldierTypeAction(1, "red", 1, "RANGED"))

        assertEquals(expectedActions, actions)
    }

    @Test(expected = RuntimeException::class)
    fun whenParsingJavascriptExecutorCallResultAndHavingErrors_shouldPrintThrowRunTimeException() {
        val gameState = File("testResources/simpleGameState.json").readText()
        val executor = JavascriptExecutor("testResources/demoJsFailCode.js")
        executor.callExecutor(gameState, GameJsonParser())
    }
}