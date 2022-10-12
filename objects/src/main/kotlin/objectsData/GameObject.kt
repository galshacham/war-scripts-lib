package objectsData

import Globals.Companion.OBJECT_JSON_SERIALIZER_REF
import enums.ObjectTypeEnum
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

// TODO: add serialization tests

object GameObjectModuleSerializer : JsonContentPolymorphicSerializer<GameObject>(GameObject::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out GameObject> {
        return when (element.jsonObject[OBJECT_JSON_SERIALIZER_REF]?.jsonPrimitive?.content) {
            ObjectTypeEnum.MELEE.type -> MeleeSoldier.serializer()
            ObjectTypeEnum.RANGED.type -> RangedSoldier.serializer()
            ObjectTypeEnum.CASTLE.type -> Castle.serializer()
            else -> throw Exception("Unknown GameObject: key 'type' not found or does not matches any module type")
        }
    }
}

@Serializable(with = GameObjectModuleSerializer::class)
abstract class GameObject() {
    abstract val type: ObjectTypeEnum
    abstract val id: Int
    abstract var loc: Loc
}


