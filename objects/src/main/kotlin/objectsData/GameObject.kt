package objectsData

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive


// TODO: You can do this with enum!
object GameObjectModuleSerializer : JsonContentPolymorphicSerializer<GameObject>(GameObject::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out GameObject> {
        return when (element.jsonObject["type"]?.jsonPrimitive?.content) {
            "MELEE" -> MeleeSoldier.serializer()
            "RANGED" -> RangedSoldier.serializer()
            "CASTLE" -> Castle.serializer()
            else -> throw Exception("Unknown GameObject: key 'type' not found or does not matches any module type")
        }
    }
}

@Serializable(with = GameObjectModuleSerializer::class)
abstract class GameObject() {
    abstract val type: String
    abstract val id: Int
    abstract var loc: Loc
}


