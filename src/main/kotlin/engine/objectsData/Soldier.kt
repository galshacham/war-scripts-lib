package engine.objectsData

abstract class Soldier(
    override val id: String,
    override var loc: Loc,
    open val speed: Int
) : GameObject(id, loc) {
}
