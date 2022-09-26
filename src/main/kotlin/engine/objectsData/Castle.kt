package engine.objectsData

data class Castle(override val id: Int, override var loc: Loc) : GameObject(id, loc)
