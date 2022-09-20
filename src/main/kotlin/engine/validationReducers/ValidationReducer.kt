package engine.validationReducers

import engine.actionsData.ActionData
import engine.objectsData.GameData

interface ValidationReducer {
    fun validate(gameData: GameData, actions: List<ActionData>): List<ActionData>
}