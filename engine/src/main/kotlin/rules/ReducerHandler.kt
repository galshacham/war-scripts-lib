package rules

import actionsData.Action
import objectsData.Game
import rules.interfaces.IReducer
import rules.states.IState
import rules.states.StateManager

class ReducerHandler(
    private val reducers: List<IReducer>,
    private val stateManager: StateManager
) {
    fun setState(game: Game, action: Action): Game {
        val stateList = reducers.map { stateManager.getState(game, it) }.toMutableList()

        if (reducers.all {
                it.validateAction(stateList[reducers.indexOf(it)], action)
            }) {
            reducers.forEachIndexed { index, reducer -> stateList[index] = reducer.setState(stateList[index], action) }
        } else {
            reducers.forEach { it.ignoreAction(action) }
        }

        return stateManager.mergeState(stateList)
    }
}
