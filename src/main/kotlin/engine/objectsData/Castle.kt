package engine.objectsData

import bigComm.enums.SoldierTypeEnum

data class Castle(override val id: Int, override var loc: Loc, val soldierType: SoldierTypeEnum) : GameObject(id, loc)
