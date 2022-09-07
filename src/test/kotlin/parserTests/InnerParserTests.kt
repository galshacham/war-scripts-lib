package tests.parserTests

import comm.inner.ActionData
import comm.inner.AttackSoldierActionData
import comm.inner.ChangeSoldierActionData
import enums.ActionTypeEnum
import enums.SoldierTypeEnum
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import parsers.InnerParser
import kotlin.test.assertEquals

class InnerParserTests {
    val parser = InnerParser();

    @Test
    fun givenActionData_whenActionDataIsChangeSoldierAction_shouldParseToChangeSoldierAction() {
        val actionJsonString = """
              {
                "activatorId": 1,
                "actionType": "CHANGE_SOLDIER_TYPE",
                "actionData": {
                    "creatingSoldierType": "RANGED"
                }
              }
        """.trimIndent()

        val actualActionData = parser.parseToObject(actionJsonString)

        val soldierChangeActionData = ActionData(
            1,
            ActionTypeEnum.CHANGE_SOLDIER_TYPE,
            ChangeSoldierActionData(SoldierTypeEnum.RANGED)
        )

        assertEquals(soldierChangeActionData, actualActionData)
    }

    @Test
    fun givenActionData_whenActionDataIsAttackSoldierAction_shouldParseToAttackSoldierAction() {
        val actionJsonString = """
              {
                "activatorId": 1,
                "actionType": "ATTACK_SOLDIER_TYPE",
                "actionData": {
                    "attackedSoldierId": "60"
                }
              }
        """.trimIndent()

        val actualActionData = parser.parseToObject(actionJsonString)

        val soldierChangeActionData = ActionData(
            1,
            ActionTypeEnum.ATTACK_SOLDIER_TYPE,
            AttackSoldierActionData(60)
        )

        assertEquals(soldierChangeActionData, actualActionData)
    }
}