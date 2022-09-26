package engine.objectsData

data class RangedSoldier(override val id: Int, override var loc: Loc) : Soldier(id, loc, 4)