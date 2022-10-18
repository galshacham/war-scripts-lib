package rules

import actionsData.Action
import objectsData.Game
import rules.interfaces.IReducer
import rules.states.IState
import rules.states.StateManager

class ReducerHandler(
    private val reducers: List<IReducer<IState>>,
    private val stateManager: StateManager
) {
    fun setState(game: Game, action: Action): Game {
        val states: List<IState> = if (validateAllReducers(game, action)) {
            reducers.map { it.setState(stateManager.getState(game, it), action) }
        } else {
            reducers.forEach { it.ignoreAction(action) }
            mutableListOf()
        }

        return stateManager.mergeState(states)
    }

    private fun validateAllReducers(game: Game, action: Action) =
        reducers.all { it.validateAction(stateManager.getState(game, it), action) }
}
