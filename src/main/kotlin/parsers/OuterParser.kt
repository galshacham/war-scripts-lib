package parsers

import comm.inner.ActionData
import comm.inner.AttackSoldierActionData
import comm.inner.ChangeSoldierActionData
import comm.outer.ObjectData
import comm.outer.objectsData.ObjectDataInterface
import enums.ActionTypeEnum
import enums.GameObjectsTypesEnums
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

class OuterParser {
    inline fun <reified ADDITIONAL_DATA : ObjectDataInterface> parseToJson(objectData: ObjectData<ADDITIONAL_DATA>): String {
        return Json.encodeToString(objectData)
    }
}
