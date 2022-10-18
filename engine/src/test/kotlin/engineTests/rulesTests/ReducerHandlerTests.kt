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
    @Test
    fun `GIVEN valid action duo to game WHEN reducer manager sets state SHOULD validate and execute action`() {
        val reducer = mockk<IReducer<IState>>()
        val stateManager = mockk<StateManager>()

        val currentGame = mockk<Game>()
        val state = mockk<IState>()
        val action = mockk<Action>()
        val expectedState = mockk<IState>()
        val expectedGame = mockk<Game>()

        every { stateManager.getState(currentGame, reducer) } returns state
        every { reducer.validateAction(state, action) } returns true
        every { reducer.setState(state, action) } returns expectedState
        every { stateManager.mergeState(listOf(expectedState)) } returns expectedGame

        val handler = ReducerHandler(listOf(reducer), stateManager)

        handler.setState(currentGame, action)

        verify {
            stateManager.getState(currentGame, reducer)
            reducer.validateAction(state, action)
            reducer.setState(state, action)
            stateManager.mergeState(listOf(expectedState))
        }
    }

//    @Test
//    fun `GIVEN invalid action duo to game WHEN reducer manager sets state SHOULD validate, ignore execute action and log`() {
//        val reducer = mockk<IReducer>()
//
//        val currentState = mockk<Game>()
//        val action = mockk<Action>()
//
//        every { reducer.validateAction(currentState, action) } returns false
//        justRun { reducer.ignoreAction(action) }
//
//        val handler = ReducerHandler(listOf(reducer))
//
//        handler.setState(currentState, action)
//
//        verify { reducer.ignoreAction(action) }
//        verify { reducer.validateAction(currentState, action) }
//        verify(inverse = true) { reducer.setState(currentState, action) }
//    }
//
//    @Test
//    fun `GIVEN two reducers and valid action duo to game WHEN reducer manager sets state SHOULD validate execute action`() {
//        val reducer1 = mockk<IReducer>()
//        val reducer2 = mockk<IReducer>()
//
//        val currentState = mockk<Game>()
//        val action = mockk<Action>()
//
//        every { reducer1.validateAction(currentState, action) } returns true
//        every { reducer2.validateAction(currentState, action) } returns true
//
//        val handler = ReducerHandler(listOf(reducer1, reducer2))
//
//        handler.setState(currentState, action)
//
//        verify { reducer1.validateAction(currentState, action) }
//        verify { reducer1.setState(currentState, action) }
//
//        verify { reducer2.validateAction(currentState, action) }
//        verify { reducer2.setState(currentState, action) }
//    }
//
//    @Test
//    fun `GIVEN two reducers and invalid action duo to game WHEN reducer manager sets state SHOULD validate, ignore execute action and log`() {
//        val reducer1 = mockk<IReducer>()
//        val reducer2 = mockk<IReducer>()
//
//        val currentState = mockk<Game>()
//        val action = mockk<Action>()
//
//        every { reducer1.validateAction(currentState, action) } returns true
//        justRun { reducer1.ignoreAction(action) }
//        every { reducer2.validateAction(currentState, action) } returns false
//        justRun { reducer2.ignoreAction(action) }
//
//        val handler = ReducerHandler(listOf(reducer1, reducer2))
//
//        handler.setState(currentState, action)
//
//        verify { reducer1.ignoreAction(action) }
//        verify { reducer1.validateAction(currentState, action) }
//        verify(inverse = true) { reducer1.setState(currentState, action) }
//
//        verify { reducer2.ignoreAction(action) }
//        verify { reducer2.validateAction(currentState, action) }
//        verify(inverse = true) { reducer2.setState(currentState, action) }
//    }
}