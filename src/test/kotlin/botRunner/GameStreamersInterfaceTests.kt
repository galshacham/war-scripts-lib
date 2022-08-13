//package tests.streamers
//
//import exceptions.BotRuntimeException
//import main.parser.GameJsonParser
//import enums.SoldierTypeEnum
//import objects.actions.ChangeSoldierTypeAction
//import org.junit.Test
//import org.junit.jupiter.api.Test
//import steamers.JavascriptStreamer
//import tests.FileTestUtils.Companion.getResourceFileText
//import kotlin.test.assertEquals
//
//class GameStreamersInterfaceTests {
//    @Test
//    fun whenParsingJavascriptStreamerCallResult_shouldReturnActionsList() {
//        val gameState = getResourceFileText("gameStreamersInterface/simpleGameAfterTurnState.json");
//        val side = 1
//        val streamer = JavascriptStreamer("testResources/gameStreamersInterface/demoJsCode.js", side)
//        val actions = streamer.call(gameState, parser.GameJsonParser())
//        val expectedActions = listOf(ChangeSoldierTypeAction("1", side, "0", SoldierTypeEnum.RANGED))
//
//        assertEquals(expectedActions, actions)
//    }
//
//    @Test(expected = BotRuntimeException::class)
//    fun whenParsingJavascriptStreamerCallResultAndHavingErrors_shouldPrintThrowRunTimeException() {
//        val gameState = getResourceFileText("gameStreamersInterface/simpleGameAfterTurnState.json");
//        val streamer = JavascriptStreamer("testResources/demoJsFailCode.js", 1)
//        streamer.call(gameState, parser.GameJsonParser())
//    }
//}