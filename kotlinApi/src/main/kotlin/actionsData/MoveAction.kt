package actionsData

import actionsData.Action
import objectsData.Loc

data class MoveAction(override val activatorId: Int, val newLoc: Loc) : Action(activatorId)