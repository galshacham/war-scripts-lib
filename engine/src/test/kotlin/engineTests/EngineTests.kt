package engineTests

import AbstractAction
import AbstractGame
import Engine
import IFilterManager
import IStateManager
import IStatusManager
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class EngineTests {
    private val filterMock = mockk<IFilterManager>()
    private val stateMock = mockk<IStateManager>()
    private val statusMock = mockk<IStatusManager>()
    private val engine = Engine(filterMock, stateMock, statusMock)
    private val initialAbstractGameState = mockk<AbstractGame>()
    private val abstractGameStateAfterChange = mockk<AbstractGame>()
    private val abstractGameStateAfterPost = mockk<AbstractGame>()


    @Test
    fun `GIVEN valid actions WHEN engine runs turn SHOULD run all actions and return new game state`() {
        val abstractActions = listOf<AbstractAction>(mockk(), mockk())

        every { filterMock.filterActions(initialAbstractGameState, abstractActions) } returns abstractActions
        every { stateMock.setState(initialAbstractGameState, abstractActions) } returns abstractGameStateAfterChange
        every { stateMock.postSetState(abstractGameStateAfterChange) } returns abstractGameStateAfterPost

        val actualGame = engine.runTurn(initialAbstractGameState, abstractActions)

        verifyOrder {
            filterMock.filterActions(initialAbstractGameState, abstractActions)
            stateMock.setState(initialAbstractGameState, abstractActions)
            stateMock.postSetState(abstractGameStateAfterChange)
        }

        assertEquals(actualGame, abstractGameStateAfterPost)
    }

    @Test
    fun `GIVEN both valid and invalid WHEN engine runs turn SHOULD filter all invalid actions`() {
        val valid = mockk<AbstractAction>()
        val invalid1 = mockk<AbstractAction>()
        val invalid2 = mockk<AbstractAction>()
        val actions = listOf(invalid1, valid, invalid2)
        val validActions = listOf(valid)
        every { filterMock.filterActions(initialAbstractGameState, actions) } returns validActions
        every { stateMock.setState(initialAbstractGameState, validActions) } returns abstractGameStateAfterChange
        every { stateMock.postSetState(abstractGameStateAfterChange) } returns abstractGameStateAfterPost

        engine.runTurn(initialAbstractGameState, actions)

        verify {
            filterMock.filterActions(initialAbstractGameState, actions)
            stateMock.setState(initialAbstractGameState, validActions)
            stateMock.postSetState(abstractGameStateAfterChange)
        }
    }

    @Test
    fun `WHEN checking if game is over SHOULD return true when status manager returns true`() {
        every { statusMock.validateGameOver(initialAbstractGameState) } returns true

        assertTrue(engine.isOver(initialAbstractGameState))
    }

    @Test
    fun `WHEN checking if game is over SHOULD return false when status manager returns false`() {
        every { statusMock.validateGameOver(initialAbstractGameState) } returns false

        assertFalse(engine.isOver(initialAbstractGameState))
    }
}