package engine.objectsData

data class RangedSoldier(override val id: String, override var loc: Loc) : Soldier(id, loc)