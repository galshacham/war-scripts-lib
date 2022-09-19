package tests.parserTests

import comm.outer.LocData
import comm.outer.ObjectData
import comm.outer.objectsData.CastleData
import enums.GameObjectsTypesEnums
import enums.SoldierTypeEnum
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import parsers.OuterParser
import tests.TestConstants.Companion.CASTLE_OBJECT_JSON
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