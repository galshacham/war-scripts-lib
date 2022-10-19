package engineTests.rulesTests

import actionsData.Action
import io.mockk.*
import objectsData.Game
import org.junit.jupiter.api.Test
import rules.ReducerHandler
import rules.interfaces.IReducer
import rules.states.IState
import rules.states.StateManager

// I think I should implement the deepCopy in the engine instead of the handler since the handler just handles an action
// It doesn't care, it doesn't have a state like the engine which should save a state (old or new)
// There still will be a finale stage since the reducerHandler cannot just kill someone.
// But the finale stage can use the reducer to perform certain actions such as KILL, TURN, CREATE...
class ReducerHandlerTests {
    private val reducer1 = mockk<IReducer<IState>>()
    private val reducer2 = mockk<IReducer<IState>>()
    private val stateManager = mockk<StateManager>()
    private val currentGame = mockk<Game>()
    private val state1 = mockk<IState>()
    private val state2 = mockk<IState>()
    private val expectedState1 = mockk<IState>()
    private val expectedState2 = mockk<IState>()
    private val expectedGame = mockk<Game>()
    private val action = mockk<Action>()

    @Test
    fun `GIVEN valid action according to game WHEN reducer manager sets state SHOULD validate and execute action`() {
        every { stateManager.getState(currentGame, reducer1) } returns state1
        every { reducer1.setState(state1, action) } returns expectedState1
        every { reducer1.validateAction(state1, action) } returns true
        every { stateManager.mergeState(listOf(expectedState1)) } returns expectedGame

        val handler = ReducerHandler(listOf(reducer1), stateManager)

        handler.setState(currentGame, action)

        verify {
            stateManager.mergeState(listOf(expectedState1))
        }
    }

    @Test
    fun `GIVEN invalid action according to game WHEN reducer manager sets state SHOULD validate, ignore execute action and log`() {
        every { stateManager.getState(currentGame, reducer1) } returns state1
        every { reducer1.validateAction(state1, action) } returns false
        justRun { reducer1.ignoreAction(action) }
        every { stateManager.mergeState(listOf()) } returns expectedGame

        val handler = ReducerHandler(listOf(reducer1), stateManager)

        handler.setState(currentGame, action)

        verify {
            reducer1.ignoreAction(action)
            stateManager.mergeState(listOf())
        }
        verify(inverse = true) {
            reducer1.setState(any(), any())
        }
    }

    @Test
    fun `GIVEN two reducers and valid action duo to game WHEN reducer manager sets state SHOULD validate execute action`() {
        val handler = ReducerHandler(listOf(reducer1, reducer2), stateManager)


        every { stateManager.getState(currentGame, reducer1) } returns state1
        every { stateManager.getState(currentGame, reducer2) } returns state2

        every { reducer1.validateAction(state1, action) } returns true
        every { reducer2.validateAction(state2, action) } returns true

        every { reducer1.setState(state1, action) } returns expectedState1
        every { reducer2.setState(state2, action) } returns expectedState2
        every { stateManager.mergeState(listOf(expectedState1, expectedState2)) } returns expectedGame

        handler.setState(currentGame, action)

        verify {
            stateManager.mergeState(listOf(expectedState1, expectedState2))
        }
    }

    @Test
    fun `GIVEN two reducers and invalid action duo to game WHEN reducer manager sets state SHOULD validate, ignore execute action and log`() {
        val handler = ReducerHandler(listOf(reducer1, reducer2), stateManager)


        every { stateManager.getState(currentGame, reducer1) } returns state1
        every { stateManager.getState(currentGame, reducer2) } returns state2

        every { reducer1.validateAction(state1, action) } returns true
        every { reducer2.validateAction(state2, action) } returns false

        justRun { reducer2.ignoreAction(action) }

        every { reducer1.setState(state1, action) } returns expectedState1
        every { reducer2.setState(state2, action) } returns expectedState2
        every { stateManager.mergeState(listOf()) } returns expectedGame

        handler.setState(currentGame, action)

        verify {
            reducer2.ignoreAction(action)
            stateManager.mergeState(listOf())
        }

        verify(inverse = true) {
            reducer1.setState(any(), any())
            reducer2.setState(any(), any())
        }
    }
}