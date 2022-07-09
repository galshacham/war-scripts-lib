package parser

import com.google.gson.*
import enums.GameObjectsTypesEnums
import exceptions.NoSuchGameObjectException
import objects.Castle
import objects.GameObject
import objects.Location
import java.lang.reflect.Type

val OBJECT_TYPE = "objectType"


class GameObjectDeserializer(private val gson: Gson) : JsonDeserializer<GameObject> {
    override fun deserialize(element: JsonElement, gameObject: Type, gsonDeserilizer: JsonDeserializationContext): GameObject {
        val jsonObject = (element as JsonObject)
        val gameObjectType: GameObjectsTypesEnums = getGameObjectType(jsonObject)

        // TODO : in the future we will extract these files to props file, so that you can add any prop you had like :)
        val id = jsonObject["id"].asString
        val owner = jsonObject["owner"].asInt
        val loc = gson.fromJson(jsonObject["loc"], Location::class.java)

        return when (gameObjectType) {
            GameObjectsTypesEnums.CASTLE -> {
                Castle(id, owner, loc)
            }
            else -> throw NoSuchGameObjectException("AA")
        }
    }

    private fun getGameObjectType(jsonObject: JsonObject): GameObjectsTypesEnums {
        try {
            return GameObjectsTypesEnums.valueOf(jsonObject[OBJECT_TYPE].asString)

        } catch (e: IllegalArgumentException) {
            throw NoSuchGameObjectException("The object you are trying to create is of enum type ${jsonObject[OBJECT_TYPE]} which does not exist in GameObjectsTypesEnums")
        }
    }
}