package engine.validationReducers

import engine.actionsData.ActionData
import engine.objectsData.GameData

class DuplicateActionsReducer : IValidationReducer {
    override fun validate(gameData: GameData, actions: List<ActionData>): List<ActionData> {
        val idSet = HashSet<String>()
        val filteredList = mutableListOf<ActionData>()
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