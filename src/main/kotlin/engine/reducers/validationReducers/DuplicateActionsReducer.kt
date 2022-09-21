package engine.reducers.validationReducers

import engine.actionsData.Action
import engine.objectsData.Game

class DuplicateActionsReducer : IValidationReducer {
    override fun validate(game: Game, actions: List<Action>): List<Action> {
        val idSet = HashSet<String>()
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