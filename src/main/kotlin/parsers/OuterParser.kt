package parsers

import comm.outer.GameData
import comm.outer.ObjectData
import comm.outer.objectsData.ObjectDataInterface
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class OuterParser {
    inline fun <reified ADDITIONAL_DATA : ObjectDataInterface> parseToJson(objectData: ObjectData<ADDITIONAL_DATA>): String {
        return Json.encodeToString(objectData)
    }
}
