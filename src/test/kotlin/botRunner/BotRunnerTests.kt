package tests.botRunner

import Constants.Companion.JS_RUNNER_PATH
import Constants.Companion.PY_RUNNER_PATH
import botRunner.BotRunner
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test
import tests.TestConstants.Companion.JS_VALID_BOT_PATH
import tests.TestConstants.Companion.PY_VALID_BOT_PATH

class BotRunnerTests {

    @Test
    fun whenCreatingJavascriptBotRunner_shouldCreateAProcessWithInitiativesValues() {
        val runtimeSpy = spyk(Runtime.getRuntime());
        val side = 1
        val execProgram = "node $JS_RUNNER_PATH"
        val botPath = JS_VALID_BOT_PATH

        val expectedExecString = "$execProgram $botPath $side"

        BotRunner(botPath, side, runtimeSpy, execProgram)

        verify { runtimeSpy.exec(expectedExecString) }
    }

    @Test
    fun whenCreatingPythonBotRunner_shouldCreateAProcessWithInitiativesValues() {
        val runtimeSpy = spyk(Runtime.getRuntime());
        val side = 1
        val execProgram = "py $PY_RUNNER_PATH"
        val botPath = PY_VALID_BOT_PATH

        val expectedExecString = "$execProgram $botPath $side"
        BotRunner(botPath, side, runtimeSpy, execProgram)

        verify { runtimeSpy.exec(expectedExecString) }
    }

    // Integ test, for now it will be held
//    @Test
//    fun givenAValidBot_whenRunningABotRunnerTurn_shouldRunTurn() {
//        val player = 0
//        val execProgram = "node $JS_RUNNER_PATH"
//        val botPath = JS_VALID_BOT_PATH
//        val gameStateJson = SIMPLE_GAME_STATE_JSON
//        val expectedActions =
//            listOf(ChangeSoldierAction(0, ActionTypeEnum.CHANGE_SOLDIER_TYPE, SoldierTypeEnum.RANGED))
//
//        val botRunner = BotRunner(botPath, player, Runtime.getRuntime(), execProgram)
//
//        val module = SerializersModule {
//            polymorphic(Action::class, ChangeSoldierAction::class, ChangeSoldierAction.serializer());
//        }
//
//
//        val string = botRunner.doTurn(gameStateJson)
//        val actualActions = Json { serializersModule = module }.decodeFromString<List<Action>>(string)
//
//        assertEquals(expectedActions, actualActions)
//    }
}