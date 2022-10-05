package botRunner

import Constants.Companion.JS_RUNNER_PATH
import org.junit.jupiter.api.Test
import TestConstants.Companion.JS_VALID_BOT_PATH
import exceptions.BotRuntimeException
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.assertThrows
import java.io.ByteArrayInputStream
import java.io.OutputStream
import kotlin.test.assertEquals

class BotRunnerTests {

    @Test
    fun whenBotRunnerDoTurn_shouldSendStringAndReceiveStringAnswer() {
        val runtimeMock = mockk<Runtime>()
        val processMock = mockk<Process>()
        val outputStreamMock = mockk<OutputStream>()

        val side = 1
        val execProgram = "node $JS_RUNNER_PATH"
        val botPath = JS_VALID_BOT_PATH

        val someGameState = "Hello Omer!"
        val expectedGameState = "This is the new gameState!"
        val inputStream = ByteArrayInputStream(expectedGameState.toByteArray())

        every { runtimeMock.exec("$execProgram $botPath $side") } returns processMock
        every { processMock.inputStream } returns inputStream
        every { processMock.outputStream } returns outputStreamMock
        every { outputStreamMock.write(someGameState.toByteArray()) } returns Unit
        every { outputStreamMock.flush() } returns Unit

        val bot = BotRunner(botPath, side, runtimeMock, execProgram)

        val actualGameState = bot.doTurn(someGameState)

        assertEquals(expectedGameState, actualGameState)
    }

    @Test
    fun whenBotRunnerDoTurnFails_shouldSendStringAndErrorMessage() {
        val runtimeMock = mockk<Runtime>()
        val processMock = mockk<Process>()
        val outputStreamMock = mockk<OutputStream>()

        val side = 1
        val execProgram = "node $JS_RUNNER_PATH"
        val botPath = JS_VALID_BOT_PATH

        val someGameState = "Hello Omer!"
        val expectedErrorMessage = "This is the new gameState!"
        val inputStream = ByteArrayInputStream("".toByteArray())
        val errorStream = ByteArrayInputStream(expectedErrorMessage.toByteArray())

        every { runtimeMock.exec("$execProgram $botPath $side") } returns processMock
        every { processMock.inputStream } returns inputStream
        every { processMock.errorStream } returns errorStream
        every { processMock.outputStream } returns outputStreamMock
        every { outputStreamMock.write(someGameState.toByteArray()) } returns Unit
        every { outputStreamMock.flush() } returns Unit

        val bot = BotRunner(botPath, side, runtimeMock, execProgram)

        val message = assertThrows<BotRuntimeException> {
            val actualGameState = bot.doTurn(someGameState)
        }.message

        assertEquals("Compilation error of bot, stacktrace presented here:\n$expectedErrorMessage", message)
    }
}