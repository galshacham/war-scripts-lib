package engine.objectsData

@kotlinx.serialization.Serializable
data class RangedSoldier(override val id: Int, override var loc: Loc) : Soldier() {
    override val speed: Int = 4
}