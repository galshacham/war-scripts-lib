package bigComm//import bigComm.ActionTypeEnum
//import bigComm.GameObjectsTypesEnums
//import bigComm.NoSuchActionException
//import kotlinx.serialization.DeserializationStrategy
//import kotlinx.serialization.decodeFromString
//import kotlinx.serialization.encodeToString
//import kotlinx.serialization.json.*
//import kotlinx.serialization.modules.SerializersModule
//import objects.Castle
//import bigComm.Game
//
//class Parser() {
//    val json: Json
//
//    init {
//        val module = SerializersModule {
//            polymorphic(Action::class, ChangeSoldierAction::class, ChangeSoldierAction.serializer());
//
//            polymorphic(GameObject::class, Castle::class, Castle.serializer());
//        }
//
//        json = Json { serializersModule = module }
//    }
//
//    fun fromString(actionsJsonString: String): List<Action> {
//        return json.decodeFromString(actionsJsonString)
//    }
//
//    fun fromObject(game: Game): String {
//        return json.encodeToString(game)
//    }
//}
//
//private const val ACTION_TYPE_KEY = "actionType"
//private const val GAME_OBJECT_TYPE_KEY = "objectType"
//
//object ActionSerializer : JsonContentPolymorphicSerializer<Action>(Action::class) {
//    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Action> {
//        return when (val actionType = element.jsonObject[ACTION_TYPE_KEY]?.jsonPrimitive?.content) {
//            ActionTypeEnum.CHANGE_SOLDIER_TYPE.toString() -> ChangeSoldierAction.serializer()
//            else -> throw NoSuchActionException("Action $actionType does not exist")
//        }
//    }
//}
//
//object GameObjectSerializer : JsonContentPolymorphicSerializer<GameObject>(GameObject::class) {
//    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out GameObject> {
//        return when (val objectType = element.jsonObject[GAME_OBJECT_TYPE_KEY]?.jsonPrimitive?.content) {
//            GameObjectsTypesEnums.CASTLE.toString() -> Castle.serializer()
//            else -> throw NoSuchActionException("Action $objectType does not exist")
//        }
//    }
//}
//
