package bigComm.parsers

import bigComm.comm.outer.ObjectData
import bigComm.comm.outer.objectsData.ObjectDataInterface
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class OuterParser {
    inline fun <reified ADDITIONAL_DATA : ObjectDataInterface> parseToJson(objectData: ObjectData<ADDITIONAL_DATA>): String {
        return Json.encodeToString(objectData)
    }
}
