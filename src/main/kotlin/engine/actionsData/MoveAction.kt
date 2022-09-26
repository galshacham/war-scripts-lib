package engine.actionsData

import engine.objectsData.Loc

data class MoveAction(override val activatorId: Int, val newLoc: Loc) : Action(activatorId)