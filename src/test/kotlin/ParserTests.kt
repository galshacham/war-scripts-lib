package tests

import Parser
import enums.ActionTypeEnum
import enums.SoldierTypeEnum
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import objects.actions.Action
import objects.actions.ChangeSoldierAction
import org.junit.jupiter.api.Test
import tests.TestConstants.Companion.ACTIONS_JSON
import kotlin.test.assertEquals

@Serializable(with = ModuleSerializer::class)
sealed class Module {
    abstract val type: String
}

@kotlinx.serialization.Serializable
data class TeamModule(val data: TeamData, override val type: String = "team") : Module()

@kotlinx.serialization.Serializable
data class OrgModule(val string: String, override val type: String = "org") : Module()

@kotlinx.serialization.Serializable
data class TeamData(
    val readOnly: Boolean = false
)

object ModuleSerializer : JsonContentPolymorphicSerializer<Module>(Module::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Module> {
        return when (element.jsonObject["type"]?.jsonPrimitive?.content) {
            "team" -> TeamModule.serializer()
            "org" -> OrgModule.serializer()
            else -> throw Exception("Unknown Module: key 'type' not found or does not matches any module type")
        }
    }
}

class ParserTests {
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        prettyPrint = true
        encodeDefaults = true
        classDiscriminator = "#class"
    }

    @Test
    fun whenParsingActionsJson_shouldParseToObjects() {
        val actionsJsonString = ACTIONS_JSON

        val parser = Parser();
        val actualActions: List<Action> = parser.fromString(actionsJsonString)

        val expectedActions = listOf(
            ChangeSoldierAction(60, SoldierTypeEnum.RANGED),
            ChangeSoldierAction(70, SoldierTypeEnum.MELEE)
        )

        assertEquals(expectedActions, actualActions)
//
//        val inputJson = """
//    [
//    {"type": "team","data": {"readOnly": true}},
//    {"type": "org","string": "hello"}
//    ]
//""".trimIndent()
//
//        val moduleList: List<Module> =
//            json.decodeFromJsonElement(json.parseToJsonElement(inputJson))
//        println(moduleList)
    }
}
