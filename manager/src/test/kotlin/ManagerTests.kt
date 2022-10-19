import actionsData.Action
import botRunner.BotRunnerFactory
import botRunner.IBotRunner
import exceptions.NoArgumentsException
import io.mockk.*
import objectsData.Game
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
        val jsonHandlerMock = mockk<JsonHandler>()
        val botRunnerMock = mockk<IBotRunner>()

        val botPath = "validBot"

        every { factoryMock.createBotRunner(botPath, 0) } returns botRunnerMock

        val turn1StringState = "g1"
        val turn2StringState = "g2"
        val turn3StringState = "g3"
        // There are 4 stringStates but only 3 objectState since the objectState is generated after checking if the game is over
        val turn1State = mockk<Game>()
        val turn2State = mockk<Game>()
        val turn3State = mockk<Game>()
        val turn4State = mockk<Game>()

        val turn1Actions = listOf(mockk<Action>())
        val turn2Actions = listOf(mockk<Action>())
        val turn3Actions = listOf(mockk<Action>())

        val turn1StringActions = "a1"
        val turn2StringActions = "a2"
        val turn3StringActions = "a3"

        every { jsonHandlerMock.parseJsonToGame(turn1StringState) } returns turn1State

        every { engineMock.isOver(turn1State) } returns false
        every { engineMock.isOver(turn2State) } returns false
        every { engineMock.isOver(turn3State) } returns false
        every { engineMock.isOver(turn4State) } returns true

        every { jsonHandlerMock.parseGameToJson(turn1State) } returns turn1StringState
        every { jsonHandlerMock.parseGameToJson(turn2State) } returns turn2StringState
        every { jsonHandlerMock.parseGameToJson(turn3State) } returns turn3StringState

        every { botRunnerMock.doTurn(turn1StringState) } returns turn1StringActions
        every { botRunnerMock.doTurn(turn2StringState) } returns turn2StringActions
        every { botRunnerMock.doTurn(turn3StringState) } returns turn3StringActions

        every { jsonHandlerMock.parseJsonToActions(turn1StringActions) } returns turn1Actions
        every { jsonHandlerMock.parseJsonToActions(turn2StringActions) } returns turn2Actions
        every { jsonHandlerMock.parseJsonToActions(turn3StringActions) } returns turn3Actions

        every { engineMock.runTurn(turn1State, turn1Actions) } returns turn2State
        every { engineMock.runTurn(turn2State, turn2Actions) } returns turn3State
        every { engineMock.runTurn(turn3State, turn3Actions) } returns turn4State

        val manager = Manager(factoryMock, engineMock, jsonHandlerMock)

        manager.init(botPath)
        manager.runGame(turn1StringState)

        verifyAll {
            jsonHandlerMock.parseJsonToGame(turn1StringState)

            engineMock.isOver(turn1State)
            engineMock.isOver(turn2State)
            engineMock.isOver(turn3State)
            engineMock.isOver(turn4State)

            jsonHandlerMock.parseGameToJson(turn1State)
            jsonHandlerMock.parseGameToJson(turn2State)
            jsonHandlerMock.parseGameToJson(turn3State)

            botRunnerMock.doTurn(turn1StringState)
            botRunnerMock.doTurn(turn2StringState)
            botRunnerMock.doTurn(turn3StringState)

            jsonHandlerMock.parseJsonToActions(turn1StringActions)
            jsonHandlerMock.parseJsonToActions(turn2StringActions)
            jsonHandlerMock.parseJsonToActions(turn3StringActions)

            engineMock.runTurn(turn1State, turn1Actions)
            engineMock.runTurn(turn2State, turn2Actions)
            engineMock.runTurn(turn3State, turn3Actions)
        }
    }

}