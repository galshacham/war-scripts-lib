package engine.actionsData

import engine.objectsData.Loc

data class MoveAction(override val activatorId: String, val newLoc: Loc) : Action(activatorId)