import enums.ActionTypeEnum
import exceptions.NoSuchActionException
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*
import kotlinx.serialization.modules.SerializersModule
import objects.actions.Action
import objects.actions.ChangeSoldierAction

class Parser() {
    val json: Json

    init {
        val module = SerializersModule {
            polymorphic(Action::class, ChangeSoldierAction::class, ChangeSoldierAction.serializer());
        }

        json = Json { serializersModule = module }
    }

    fun fromString(actionsJsonString: String): List<Action> {
        return json.decodeFromString(actionsJsonString)
    }
}

private const val ACTION_TYPE_KEY = "actionType"

object ModuleSerializer : JsonContentPolymorphicSerializer<Action>(Action::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Action> {
        return when (val actionType = element.jsonObject[ACTION_TYPE_KEY]?.jsonPrimitive?.content) {
            ActionTypeEnum.CHANGE_SOLDIER_TYPE.toString() -> ChangeSoldierAction.serializer()
            else -> throw NoSuchActionException("Action $actionType does not exist")
        }
    }
}
