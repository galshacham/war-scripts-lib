import botRunner.BotRunnerFactory
import botRunner.IBotRunner
import exceptions.NoArgumentsException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class ManagerTests {
    @Test
    fun `WHEN initiating manager without players SHOULD throw exception`() {
        val manager = Manager(mockk(), mockk(), mockk())

        val message = assertThrows<NoArgumentsException> {
            manager.init()
        }.message

        assertEquals(message, "At least one argument must be assigned")
    }

    @Test
    fun `GIVEN valid manager WHEN manager init a game with two players SHOULD add two BotRunners`() {
        val factoryMock = mockk<BotRunnerFactory>()

        val botRunnerMock = mockk<IBotRunner>()

        val botPath1 = "validPath1"
        val botPath2 = "validPath2"

        every { factoryMock.createBotRunner(botPath1, 0) } returns botRunnerMock
        every { factoryMock.createBotRunner(botPath2, 1) } returns botRunnerMock

        val manager = Manager(factoryMock, mockk(), mockk())

        manager.init(botPath1, botPath2)

        assertEquals(manager.bots.size, 2)
        verify { factoryMock.createBotRunner(botPath1, 0) }
        verify { factoryMock.createBotRunner(botPath2, 1) }
    }

    @Test
    fun `GIVEN initiated manager WHEN running manager SHOULD run bots with states until isOver returns true`() {
        val factoryMock = mockk<BotRunnerFactory>()
        val engineMock = mockk<IEngine>()
        val translatorMock = mockk<ITranslator>()
        val botRunnerMock = mockk<IBotRunner>()

        val botPath = "validBot"

        every { factoryMock.createBotRunner(botPath, 0) } returns botRunnerMock

        val turn1BufferState = "g1".toByteArray()
        val turn2BufferState = "g2".toByteArray()
        val turn3BufferState = "g3".toByteArray()
        // There are 4 stringStates but only 3 objectState since the objectState is generated after checking if the game is over
        val turn1State = mockk<AbstractGame>()
        val turn2State = mockk<AbstractGame>()
        val turn3State = mockk<AbstractGame>()
        val turn4State = mockk<AbstractGame>()

        val turn1AbstractActions = listOf(mockk<AbstractAction>())
        val turn2AbstractActions = listOf(mockk<AbstractAction>())
        val turn3AbstractActions = listOf(mockk<AbstractAction>())

        val turn1BufferActions = "a1".toByteArray()
        val turn2BufferActions = "a2".toByteArray()
        val turn3BufferActions = "a3".toByteArray()

        every { translatorMock.bufferToGame(turn1BufferState) } returns turn1State

        every { engineMock.isOver(turn1State) } returns false
        every { engineMock.isOver(turn2State) } returns false
        every { engineMock.isOver(turn3State) } returns false
        every { engineMock.isOver(turn4State) } returns true

        every { translatorMock.gameToBuffer(turn1State) } returns turn1BufferState
        every { translatorMock.gameToBuffer(turn2State) } returns turn2BufferState
        every { translatorMock.gameToBuffer(turn3State) } returns turn3BufferState

        every { botRunnerMock.doTurn(turn1BufferState) } returns turn1BufferActions
        every { botRunnerMock.doTurn(turn2BufferState) } returns turn2BufferActions
        every { botRunnerMock.doTurn(turn3BufferState) } returns turn3BufferActions

        every { translatorMock.bufferToActionList(turn1BufferActions) } returns turn1AbstractActions
        every { translatorMock.bufferToActionList(turn2BufferActions) } returns turn2AbstractActions
        every { translatorMock.bufferToActionList(turn3BufferActions) } returns turn3AbstractActions

        every { engineMock.runTurn(turn1State, turn1AbstractActions) } returns turn2State
        every { engineMock.runTurn(turn2State, turn2AbstractActions) } returns turn3State
        every { engineMock.runTurn(turn3State, turn3AbstractActions) } returns turn4State

        val manager = Manager(factoryMock, engineMock, translatorMock)

        manager.init(botPath)
        manager.runGame(turn1BufferState)

        verifyAll {
            translatorMock.bufferToGame(turn1BufferState)

            engineMock.isOver(turn1State)
            engineMock.isOver(turn2State)
            engineMock.isOver(turn3State)
            engineMock.isOver(turn4State)

            translatorMock.gameToBuffer(turn1State)
            translatorMock.gameToBuffer(turn2State)
            translatorMock.gameToBuffer(turn3State)

            botRunnerMock.doTurn(turn1BufferState)
            botRunnerMock.doTurn(turn2BufferState)
            botRunnerMock.doTurn(turn3BufferState)

            translatorMock.bufferToActionList(turn1BufferActions)
            translatorMock.bufferToActionList(turn2BufferActions)
            translatorMock.bufferToActionList(turn3BufferActions)

            engineMock.runTurn(turn1State, turn1AbstractActions)
            engineMock.runTurn(turn2State, turn2AbstractActions)
            engineMock.runTurn(turn3State, turn3AbstractActions)
        }
    }

}