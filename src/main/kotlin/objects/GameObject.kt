package objects

import GameObjectSerializer
import enums.GameObjectsTypesEnums

@kotlinx.serialization.Serializable(with = GameObjectSerializer::class)
abstract class GameObject {
    abstract val id: Int
    abstract val owner: Int
    abstract val loc: Location
    abstract val objectType: GameObjectsTypesEnums

    abstract fun updateState(game: Game)
}
