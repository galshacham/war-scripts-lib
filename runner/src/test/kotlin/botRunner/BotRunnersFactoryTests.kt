package botRunner

import Constants.Companion.JS_RUNNER_PATH
import Constants.Companion.TS_RUNNER_PATH
import exceptions.UnsupportedBotRunnerException
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import TestConstants

class BotRunnersFactoryTests {
    final val OWNER = 1
    @Test
    fun whenBotFactoryCreatingUnsupportedBotRunner_shouldThrowException() {
        val runtimeSpy = spyk(Runtime.getRuntime())
        val fact = BotRunnerFactory(runtimeSpy)

        assertThrows<UnsupportedBotRunnerException> {
            fact.createBotRunner("doesntExist.bla", OWNER)
        }
    }

    @Test
    fun whenBotFactoryCreatingJsBotRunner_shouldReturnExpectedJsBotRunner() {
        val runtimeSpy = spyk(Runtime.getRuntime())
        val fact = BotRunnerFactory(runtimeSpy)

        val botPath = TestConstants.JS_VALID_BOT_PATH
        val execProgram = "node $JS_RUNNER_PATH"
        val expectedExecString = "$execProgram $botPath $OWNER"

        fact.createBotRunner(botPath, OWNER);

        verify { runtimeSpy.exec(expectedExecString) }
    }

    @Test
    fun whenBotFactoryCreatingTsBotRunner_shouldReturnExpectedTsBotRunner() {
        val runtimeSpy = spyk(Runtime.getRuntime())
        val fact = BotRunnerFactory(runtimeSpy)

        val botPath = TestConstants.TS_VALID_BOT_PATH
        val execProgram = "node $TS_RUNNER_PATH"
        val expectedExecString = "$execProgram $botPath $OWNER"

        fact.createBotRunner(botPath, OWNER);

        verify { runtimeSpy.exec(expectedExecString) }
    }


    // TODO: Add python caller later
//    @Test
//    fun whenGameStreamerCreatesARealStreamer_shouldCreateTheStreamer() {
//        val fact = GameStreamerFactory()
//
//        val demoBotPath = "/demoPythonCode.py"
//        val pyExec = fact.createStreamer(demoBotPath)
//
//        assertEquals(demoBotPath, pyExec.codeToStreamPath)
//        assertEquals(PYTHON_Streamer_PATH, pyExec.streamerPath)
//    }
}