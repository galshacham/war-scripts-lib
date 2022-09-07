package tests.parserTests

import comm.inner.ActionData
import enums.ActionTypeEnum
import enums.SoldierTypeEnum
import org.junit.jupiter.api.Test

class InnerParserTests {
    @Test
    fun givenActionData_whenActionDataIsChangeSoldierAction_shouldParseToChangeSoldierAction() {
        val soldierChangeActionData = ActionData(
            1,
            SoldierTypeEnum.MELEE,
            ActionTypeEnum.CHANGE_SOLDIER_TYPE,
            byteArrayOf()
        )
    }
}