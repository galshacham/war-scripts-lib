package tests.botRunner

import Constants.Companion.JS_RUNNER_PATH
import Constants.Companion.PY_RUNNER_PATH
import botRunner.BotRunner
import io.mockk.spyk
import io.mockk.verify
import org.junit.Test
import tests.TestConstants.Companion.JS_VALID_BOT_PATH
import tests.TestConstants.Companion.PY_VALID_BOT_PATH
import tests.TestConstants.Companion.SIMPLE_GAME_STATE_AFTER_TURN_JSON
import tests.TestConstants.Companion.SIMPLE_GAME_STATE_JSON
import kotlin.test.assertEquals

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

    @Test
    fun givenAValidBot_whenRunningABotRunnerTurn_shouldRunTurn() {
        val player = 0
        val execProgram = "node $JS_RUNNER_PATH"
        val botPath = JS_VALID_BOT_PATH
        val gameStateJson = SIMPLE_GAME_STATE_JSON
        val expectedGameState = SIMPLE_GAME_STATE_AFTER_TURN_JSON

        val botRunner = BotRunner(botPath, player, Runtime.getRuntime(), execProgram)

        val newGameStateJson = botRunner.doTurn(gameStateJson)

        assertEquals(expectedGameState, newGameStateJson)
    }
}