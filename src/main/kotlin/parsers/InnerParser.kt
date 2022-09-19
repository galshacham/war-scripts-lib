package parsers

import comm.inner.ActionData
import comm.inner.AttackSoldierActionData
import comm.inner.ChangeSoldierActionData
import enums.ActionTypeEnum
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject

class InnerParser {
    fun parseToObjectData(jsonString: String): ActionData<*> {
        val jsonObject = Json.parseToJsonElement(jsonString)
        val objectType = ActionTypeEnum.valueOf(jsonObject.jsonObject["actionType"].toString().replace("\"", ""))

        // TODO: right now i didn't find a way to do this generic and nice? weird, but ok...
        return when (objectType) {
            ActionTypeEnum.CHANGE_SOLDIER_TYPE ->
                Json.decodeFromString<ActionData<ChangeSoldierActionData>>(jsonString)
            ActionTypeEnum.ATTACK_SOLDIER_TYPE ->
                Json.decodeFromString<ActionData<AttackSoldierActionData>>(jsonString)
            else -> TODO()
        }
    }
}