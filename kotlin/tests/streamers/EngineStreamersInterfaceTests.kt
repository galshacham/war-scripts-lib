package tests.streamers

import exceptions.BotRuntimeException
import main.GameJsonParser
import main.enums.SoldierTypeEnum
import main.objects.actions.ChangeSoldierTypeAction
import org.junit.Test
import steamers.JavascriptStreamer
import tests.FileTestUtils.Companion.getResourceFileText
import kotlin.test.assertEquals

class EngineStreamersInterfaceTests {
    @Test
    fun whenParsingJavascriptStreamerCallResult_shouldReturnActionsList() {
        val gameState = getResourceFileText("engineStreamersInterface/simpleGameState.json");
        val side = 1
        val streamer = JavascriptStreamer("testResources/engineStreamersInterface/demoJsCode.js", side)
        val actions = streamer.callStreamer(gameState, GameJsonParser())
        val expectedActions = listOf(ChangeSoldierTypeAction("1", side, "0", SoldierTypeEnum.RANGED))

        assertEquals(expectedActions, actions)
    }

    @Test(expected = BotRuntimeException::class)
    fun whenParsingJavascriptStreamerCallResultAndHavingErrors_shouldPrintThrowRunTimeException() {
        val gameState = getResourceFileText("engineStreamersInterface/simpleGameState.json");
        val streamer = JavascriptStreamer("testResources/demoJsFailCode.js", 1)
        streamer.callStreamer(gameState, GameJsonParser())
    }
}