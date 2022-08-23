import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
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