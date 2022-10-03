package objectsData

import objectsData.GameObject

@kotlinx.serialization.Serializable
abstract class Soldier() : GameObject() {
    abstract val speed: Int
}
