package engine.reducers.validationReducers

import actionsData.Action
import objectsData.Game

class DuplicateActionsReducer : IValidationReducer<Action> {
    override fun validate(game: Game, actions: List<Action>): List<Action> {
        val idSet = HashSet<Int>()
        val filteredList = mutableListOf<Action>()
        actions.forEach {
            if (!idSet.contains(it.activatorId)) {
                filteredList.add(it);
                idSet.add(it.activatorId)
            } else {
                print("Error: ignored action from activator: [${it.activatorId}], only first action committed\n")
            }
        }
        return filteredList
    }
}