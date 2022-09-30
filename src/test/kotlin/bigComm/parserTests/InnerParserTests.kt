package bigComm.parserTests

import bigComm.comm.inner.ActionData
import bigComm.comm.inner.AttackSoldierActionData
import bigComm.comm.inner.ChangeSoldierActionData
import bigComm.enums.ActionTypeEnum
import engine.enums.SoldierTypeEnum
import org.junit.jupiter.api.Test
import bigComm.parsers.InnerParser
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

        val actualActionData = parser.parseToObjectData(actionJsonString)

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

        val actualActionData = parser.parseToObjectData(actionJsonString)

        val soldierChangeActionData = ActionData(
            1,
            ActionTypeEnum.ATTACK_SOLDIER_TYPE,
            AttackSoldierActionData(60)
        )

        assertEquals(soldierChangeActionData, actualActionData)
    }
}