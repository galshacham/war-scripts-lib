package runner.botRunner

import runner.Constants.Companion.JS_RUNNER_PATH
import runner.Constants.Companion.PY_RUNNER_PATH
import runner.botRunner.BotRunner
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test
import tests.bigComm.TestConstants.Companion.JS_VALID_BOT_PATH
import tests.bigComm.TestConstants.Companion.PY_VALID_BOT_PATH

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
}