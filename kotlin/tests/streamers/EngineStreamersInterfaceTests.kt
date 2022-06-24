package tests.streamers

import exceptions.RuntimeException
import steamers.JavascriptStreamer
import main.GameJsonParser
import main.enums.SoldierTypeEnum
import main.objects.actions.ChangeSoldierTypeAction
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

class EngineStreamersInterfaceTests {
    @Test
    fun whenParsingJavascriptStreamerCallResult_shouldReturnActionsList() {
        val gameState = File("testResources/simpleGameState.json").readText()
        val streamer = JavascriptStreamer("testResources/demoJsCode.js", 1)
        val actions = streamer.callStreamer(gameState, GameJsonParser(), 0)
        val expectedActions = listOf(ChangeSoldierTypeAction("1", 0, "0", SoldierTypeEnum.RANGED))

        assertEquals(expectedActions, actions)
    }

    @Test(expected = RuntimeException::class)
    fun whenParsingJavascriptStreamerCallResultAndHavingErrors_shouldPrintThrowRunTimeException() {
        val gameState = File("testResources/simpleGameState.json").readText()
        val streamer = JavascriptStreamer("testResources/demoJsFailCode.js", 1)
        streamer.callStreamer(gameState, GameJsonParser(), 1)
    }
}