package bigComm.parserTests

import bigComm.comm.outer.LocData
import bigComm.comm.outer.ObjectData
import bigComm.comm.outer.objectsData.CastleData
import bigComm.enums.GameObjectsTypesEnums
import engine.enums.SoldierTypeEnum
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import bigComm.parsers.OuterParser
import tests.bigComm.TestConstants.Companion.CASTLE_OBJECT_JSON
import kotlin.test.assertEquals

class OuterParserTests {
    val parser = OuterParser();

    @Test
    fun givenObjectData_whenObjectDataIsCastle_shouldParseToCastleJson() {
        val expectedJson = CASTLE_OBJECT_JSON

        val castleData =
            ObjectData<CastleData>(
                1,
                2,
                LocData(3, 4),
                GameObjectsTypesEnums.CASTLE,
                CastleData(SoldierTypeEnum.MELEE)
            )

        val actualJson = parser.parseToJson(castleData)

        assertEquals(Json.parseToJsonElement(expectedJson), Json.parseToJsonElement(actualJson))
    }
}