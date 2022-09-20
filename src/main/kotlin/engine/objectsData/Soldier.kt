package engine.objectsData

data class Soldier(override val id: String, override var loc: Loc) : GameObject(id, loc)
