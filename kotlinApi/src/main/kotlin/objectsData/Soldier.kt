package objectsData

@kotlinx.serialization.Serializable
abstract class Soldier() : GameObject() {
    abstract val speed: Int
}
