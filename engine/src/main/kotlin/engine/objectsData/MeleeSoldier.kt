package engine.objectsData

data class MeleeSoldier(override val id: Int, override var loc: Loc) : Soldier() {
    override val speed: Int = 4
}