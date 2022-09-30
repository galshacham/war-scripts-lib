package engine.objectsData

import engine.enums.SoldierTypeEnum

@kotlinx.serialization.Serializable
data class Castle(override val id: Int, override var loc: Loc, val soldierType: SoldierTypeEnum) : GameObject()
