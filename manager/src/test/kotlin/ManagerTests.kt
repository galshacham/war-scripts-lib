import botRunner.BotRunnerFactory
import botRunner.IBotRunner
import exceptions.NoArgumentsException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.Ignore
import kotlin.test.assertEquals

class ManagerTests {
    @Test
    fun `WHEN initiating manager without players SHOULD throw exception`() {
        val manager = Manager(mockk())

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

        val manager = Manager(factoryMock)

        manager.init(botPath1, botPath2)

        assertEquals(manager.runners.size, 2)
        verify { factoryMock.createBotRunner(botPath1, 0) }
        verify { factoryMock.createBotRunner(botPath2, 1) }
    }

//    @Test
//    fun `GIVEN manager with bots WHEN running manager SHOULD run bots until they return false`() {
//        val factoryMock = mockk<BotRunnerFactory>()
//        val engineMock = mockk<IEngine>()
//        val botRunnerMock1 = mockk<IBotRunner>()
//        val botRunnerMock2 = mockk<IBotRunner>()
//
//        val botPath1 = "validPath1"
//        val botPath2 = "validPath2"
//
//        val firstTurnState = "SomeGameState1"
//        val secondTurnState = "SomeGameState2"
//        val thirdTurnState = "SomeGameState3"
////        val fourthTurnState = "SomeGameState4"
//
//        val firstTurnActions = "SomeReturnedActions1"
//        val secondTurnActions = "SomeReturnedActions2"
//        val thirdTurnActions = "SomeReturnedActions3"
////        val fourthTurnActions = "SomeReturnedActions4"
//
//        every { factoryMock.createBotRunner(botPath1, 0) } returns botRunnerMock1
//        every { factoryMock.createBotRunner(botPath2, 1) } returns botRunnerMock2
//
//        every { engineMock.runTurn(firstTurnState) } returns firstTurnActions
//        every { engineMock.runTurn(secondTurnState) } returns secondTurnActions
//        every { engineMock.runTurn(thirdTurnState) } returns thirdTurnActions
//
//        every { botRunnerMock1.doTurn(firstTurnState) } returns firstTurnActions
//        every { botRunnerMock1.doTurn(secondTurnState) } returns secondTurnActions
//        every { botRunnerMock1.doTurn(thirdTurnState) } returns thirdTurnActions
//
//        every { botRunnerMock2.doTurn(firstTurnState) } returns firstTurnActions
//        every { botRunnerMock2.doTurn(secondTurnState) } returns secondTurnActions
//        every { botRunnerMock2.doTurn(thirdTurnState) } returns thirdTurnActions
//
////        every { botRunnerMock1.doTurn(fourthTurnState) } returns secondTurnState // ?
////        botRunnerMock1
//
//        val manager = Manager(factoryMock)
//        manager.init(botPath1, botPath2)
//
//        manager.runGame(firstTurnState)
//
//        verify { botRunnerMock1.doTurn(firstTurnState) }
//        verify { botRunnerMock2.doTurn(firstTurnState) }
//
//        verify { botRunnerMock2.doTurn(secondTurnState) }
//        verify { botRunnerMock2.doTurn(secondTurnState) }
//
//        verify { botRunnerMock2.doTurn(thirdTurnState) }
//        verify { botRunnerMock2.doTurn(thirdTurnState) }
//    }
}