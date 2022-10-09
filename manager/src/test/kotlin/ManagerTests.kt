import actionsData.Action
import botRunner.BotRunnerFactory
import botRunner.IBotRunner
import exceptions.NoArgumentsException
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import objectsData.Game
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

    // TODO: Should i just use integ tests for this??
//    @Test
//    fun `GIVEN manager with bots WHEN running manager SHOULD run bots until they isOver returns true`() {
//        val factoryMock = mockk<BotRunnerFactory>()
//        val engineMock = mockk<IEngine>()
//        val jsonMock = mockkObject(Json)
//        val botRunnerMock1 = mockk<IBotRunner>()
//        val botRunnerMock2 = mockk<IBotRunner>()
//
//        val botPath1 = "validBot1"
//        val botPath2 = "validBot2"
//
//        val firstTurnState = mockk<Game>()
//        val secondTurnState = mockk<Game>()
//        val thirdTurnState = mockk<Game>()
//        val fourthTurnState = mockk<Game>()
//
//        val firstTurnActions1 = mockk<Action>()
//        val secondTurnActions1 = mockk<Action>()
//        val thirdTurnActions1 = mockk<Action>()
//
//        val firstTurnActions2 = mockk<Action>()
//        val secondTurnActions2 = mockk<Action>()
//        val thirdTurnActions2 = mockk<Action>()
//
//        //@formatter:off
//        every { factoryMock.createBotRunner(botPath1, 0) } returns botRunnerMock1
//        every { factoryMock.createBotRunner(botPath2, 1) } returns botRunnerMock2
//
//        every { engineMock.runTurn(firstTurnState, listOf(firstTurnActions1, firstTurnActions2)) } returns secondTurnState
//        every { engineMock.runTurn(secondTurnState, listOf(secondTurnActions1, secondTurnActions2)) } returns thirdTurnState
//        every { engineMock.runTurn(thirdTurnState, listOf(thirdTurnActions1, thirdTurnActions2)) } returns fourthTurnState
//
////        every { jsonMock. } returns fourthTurnState
//
//        every { engineMock.isOver(TODO()) } returnsMany listOf(false,false ,false, true)
//
//
//        every { botRunnerMock1.doTurn(firstTurnState) } returns firstTurnActions1
//        every { botRunnerMock1.doTurn(secondTurnState) } returns secondTurnActions1
//        every { botRunnerMock1.doTurn(thirdTurnState) } returns thirdTurnActions1
//
//        every { botRunnerMock2.doTurn(firstTurnState) } returns firstTurnActions2
//        every { botRunnerMock2.doTurn(secondTurnState) } returns secondTurnActions2
//        every { botRunnerMock2.doTurn(thirdTurnState) } returns thirdTurnActions2
//        //@formatter:on
//
//
//        val manager = Manager(factoryMock, engineMock)
//        manager.init(botPath1, botPath2)
//
//        manager.runGame(firstTurnState)
//
//        verify(exactly = 4) { engineMock.isOver() }
//        verify { botRunnerMock1.doTurn(firstTurnState) }
//        verify { botRunnerMock2.doTurn(firstTurnState) }
//        verify { engineMock.runTurn(firstTurnState, listOf(firstTurnActions1, firstTurnActions2)) }
//
//        verify { botRunnerMock1.doTurn(secondTurnState) }
//        verify { botRunnerMock2.doTurn(secondTurnState) }
//        verify { engineMock.runTurn(secondTurnState, listOf(secondTurnActions1, secondTurnActions2)) }
//
//        verify { botRunnerMock1.doTurn(thirdTurnState) }
//        verify { botRunnerMock2.doTurn(thirdTurnState) }
//        verify { engineMock.runTurn(thirdTurnState, listOf(thirdTurnActions1, thirdTurnActions2)) }
//
//    }
}