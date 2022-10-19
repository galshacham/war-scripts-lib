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
        val failingReducers = getFailingReducers(game, action)

        val states: List<IState> = if (failingReducers.isEmpty()) {
            reducers.map { it.setState(stateManager.getState(game, it), action) }
        } else {
            failingReducers.forEach { it.ignoreAction(action) }
            listOf()
        }

        return stateManager.mergeState(states)
    }

    private fun getFailingReducers(game: Game, action: Action) = reducers.filter {
        !it.validateAction(stateManager.getState(game, it), action)
    }
}
