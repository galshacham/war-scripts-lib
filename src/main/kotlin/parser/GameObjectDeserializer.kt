//package parser
//
//import enums.GameObjectsTypesEnums
//import exceptions.NoSuchGameObjectException
//import objects.Castle
//import objects.GameObject
//import objects.Location
//import objects.actions.Action
//
//val OBJECT_TYPE = "objectType"
//val ID_KEY = "id"
//val OWNER_KEY = "owner"
//val LOC_KEY = "loc"
//val ACTION_KEY = "action"
//
//
//class GameObjectDeserializer(private val gson: Gson) {
//    fun deserialize(element: JsonElement): GameObject {
//        val jsonObject = (element as JsonObject)
//        val gameObjectType: GameObjectsTypesEnums = getGameObjectType(jsonObject)
//
//        // TODO : in the future we will extract these files to props file, so that you can add any prop you had like :)
//        val id = jsonObject[ID_KEY].asInt
//        val owner = jsonObject[OWNER_KEY].asInt
//        val loc = gson.fromJson(jsonObject[LOC_KEY], Location::class.java)
//        val action = gson.fromJson(jsonObject[ACTION_KEY], Action::class.java)
//
//        return when (gameObjectType) {
//            GameObjectsTypesEnums.CASTLE -> {
//                Castle(id, owner, loc, action)
//            }
//            else -> throw NoSuchGameObjectException("AA")
//        }
//    }
//
//    private fun getGameObjectType(jsonObject: JsonObject): GameObjectsTypesEnums {
//        try {
//            return GameObjectsTypesEnums.valueOf(jsonObject[OBJECT_TYPE].asString)
//
//        } catch (e: IllegalArgumentException) {
//            throw NoSuchGameObjectException("The object you are trying to create is of enum type ${jsonObject[OBJECT_TYPE]} which does not exist in GameObjectsTypesEnums")
//        }
//    }
//}