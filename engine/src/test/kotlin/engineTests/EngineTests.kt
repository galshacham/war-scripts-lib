package engineTests

import Action
import Engine
import Game
import IFilterManager
import IStateManager
import IStatusManager
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class EngineTests {
    private val filterMock = mockk<IFilterManager>()
    private val stateMock = mockk<IStateManager>()
    private val statusMock = mockk<IStatusManager>()
    private val engine = Engine(filterMock, stateMock, statusMock)
    private val initialGameState = mockk<Game>()
    private val gameStateAfterChange = mockk<Game>()
    private val gameStateAfterPost = mockk<Game>()


    @Test
    fun `GIVEN valid actions WHEN engine runs turn SHOULD run all actions and return new game state`() {
        val actions = listOf<Action>(mockk(), mockk())

        every { filterMock.filterActions(initialGameState, actions) } returns actions
        every { stateMock.setState(initialGameState, actions) } returns gameStateAfterChange
        every { stateMock.postSetState(gameStateAfterChange) } returns gameStateAfterPost

        val actualGame = engine.runTurn(initialGameState, actions)

        verifyOrder {
            filterMock.filterActions(initialGameState, actions)
            stateMock.setState(initialGameState, actions)
            stateMock.postSetState(gameStateAfterChange)
        }

        assertEquals(actualGame, gameStateAfterPost)
    }

    @Test
    fun `GIVEN both valid and invalid WHEN engine runs turn SHOULD filter all invalid actions`() {
        val valid = mockk<Action>()
        val invalid1 = mockk<Action>()
        val invalid2 = mockk<Action>()
        val actions = listOf(invalid1, valid, invalid2)
        val validActions = listOf(valid)
        every { filterMock.filterActions(initialGameState, actions) } returns validActions
        every { stateMock.setState(initialGameState, validActions) } returns gameStateAfterChange
        every { stateMock.postSetState(gameStateAfterChange) } returns gameStateAfterPost

        engine.runTurn(initialGameState, actions)

        verify {
            filterMock.filterActions(initialGameState, actions)
            stateMock.setState(initialGameState, validActions)
            stateMock.postSetState(gameStateAfterChange)
        }
    }

    @Test
    fun `WHEN checking if game is over SHOULD return true when status manager returns true`() {
        every { statusMock.validateGameOver(initialGameState) } returns true

        assertTrue(engine.isOver(initialGameState))
    }

    @Test
    fun `WHEN checking if game is over SHOULD return false when status manager returns false`() {
        every { statusMock.validateGameOver(initialGameState) } returns false

        assertFalse(engine.isOver(initialGameState))
    }
}