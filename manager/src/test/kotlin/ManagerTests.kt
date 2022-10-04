import botRunner.BotRunnerFactory
import botRunner.IBotRunner
import exceptions.NoArgumentsException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class ManagerTests {
    @Test
    fun `WHEN initiating manager without players SHOULD throw exception`() {
        val manager = Manager(mockk(), mockk())

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

        val manager = Manager(factoryMock, mockk())

        manager.init(botPath1, botPath2)

        assertEquals(manager.bots.size, 2)
        verify { factoryMock.createBotRunner(botPath1, 0) }
        verify { factoryMock.createBotRunner(botPath2, 1) }
    }

    @Test
    fun `GIVEN manager with bots WHEN running manager SHOULD run bots until they return false`() {
        val factoryMock = mockk<BotRunnerFactory>()
        val engineMock = mockk<IEngine>()
        val botRunnerMock1 = mockk<IBotRunner>()
        val botRunnerMock2 = mockk<IBotRunner>()

        val botPath1 = "validBot1"
        val botPath2 = "validBot2"

        val firstTurnState = "SomeGameState1"
        val secondTurnState = "SomeGameState2"
        val thirdTurnState = "SomeGameState3"
        val fourthTurnState = "SomeGameState4"

        val firstTurnActions1 = "SomeReturnedActions1__turn_1"
        val secondTurnActions1 = "SomeReturnedActions1__turn_2"
        val thirdTurnActions1 = "SomeReturnedActions1__turn_3"

        val firstTurnActions2 = "SomeReturnedActions2__turn_1"
        val secondTurnActions2 = "SomeReturnedActions2__turn_2"
        val thirdTurnActions2 = "SomeReturnedActions2__turn_3"

        //@formatter:off
        every { factoryMock.createBotRunner(botPath1, 0) } returns botRunnerMock1
        every { factoryMock.createBotRunner(botPath2, 1) } returns botRunnerMock2

        every { engineMock.runTurn(firstTurnState, listOf(firstTurnActions1, firstTurnActions2)) } returns secondTurnState
        every { engineMock.runTurn(secondTurnState, listOf(secondTurnActions1, secondTurnActions2)) } returns thirdTurnState
        every { engineMock.runTurn(thirdTurnState, listOf(thirdTurnActions1, thirdTurnActions2)) } returns fourthTurnState

        every { engineMock.isOver(firstTurnState) } returns false
        every { engineMock.isOver(secondTurnState) } returns false
        every { engineMock.isOver(thirdTurnState) } returns false
        every { engineMock.isOver(fourthTurnState) } returns true

        every { botRunnerMock1.doTurn(firstTurnState) } returns firstTurnActions1
        every { botRunnerMock1.doTurn(secondTurnState) } returns secondTurnActions1
        every { botRunnerMock1.doTurn(thirdTurnState) } returns thirdTurnActions1

        every { botRunnerMock2.doTurn(firstTurnState) } returns firstTurnActions2
        every { botRunnerMock2.doTurn(secondTurnState) } returns secondTurnActions2
        every { botRunnerMock2.doTurn(thirdTurnState) } returns thirdTurnActions2
        //@formatter:on


        val manager = Manager(factoryMock, engineMock)
        manager.init(botPath1, botPath2)

        manager.runGame(firstTurnState)

        verify { engineMock.isOver(firstTurnState) }
        verify { botRunnerMock1.doTurn(firstTurnState) }
        verify { botRunnerMock2.doTurn(firstTurnState) }
        verify { engineMock.runTurn(firstTurnState, listOf(firstTurnActions1, firstTurnActions2)) }

        verify { engineMock.isOver(secondTurnState) }
        verify { botRunnerMock1.doTurn(secondTurnState) }
        verify { botRunnerMock2.doTurn(secondTurnState) }
        verify { engineMock.runTurn(secondTurnState, listOf(secondTurnActions1, secondTurnActions2)) }

        verify { engineMock.isOver(thirdTurnState) }
        verify { botRunnerMock1.doTurn(thirdTurnState) }
        verify { botRunnerMock2.doTurn(thirdTurnState) }
        verify { engineMock.runTurn(thirdTurnState, listOf(thirdTurnActions1, thirdTurnActions2)) }

        verify { engineMock.isOver(fourthTurnState) }
    }
}