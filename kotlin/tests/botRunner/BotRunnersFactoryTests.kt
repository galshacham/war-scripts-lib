package tests.streamers

import Constants.Companion.JS_RUNNER_PATH
import exceptions.UnsupportedBotRunnerException
import io.mockk.spyk
import io.mockk.verify
import org.junit.Test
import botRunner.BotRunnerFactory
import tests.TestConstants

class BotRunnersFactoryTests {

    final val SIDE = 1

    @Test(expected = UnsupportedBotRunnerException::class)
    fun whenBotFactoryCreatingUnsupportedBotRunner_shouldThrowException() {
        val runtimeSpy = spyk(Runtime.getRuntime())
        val fact = BotRunnerFactory(runtimeSpy)

        fact.createBotRunner("doesntExist.bla", SIDE)
    }

    @Test
    fun whenBotFactoryCreatingJsBotRunner_shouldReturnExpectedJsBotRunner() {
        val runtimeSpy = spyk(Runtime.getRuntime())
        val fact = BotRunnerFactory(runtimeSpy)

        val botPath = TestConstants.JS_VALID_BOT_PATH
        val execProgram = "node $JS_RUNNER_PATH"
        val expectedExecString = "$execProgram $botPath $SIDE"

        fact.createBotRunner(botPath, SIDE);

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