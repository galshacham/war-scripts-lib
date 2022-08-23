package tests

import Parser
import enums.SoldierTypeEnum
import exceptions.NoSuchActionException
import objects.actions.Action
import objects.actions.ChangeSoldierAction
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import tests.TestConstants.Companion.ACTIONS_JSON
import tests.TestConstants.Companion.NON_ACTION_JSON
import kotlin.test.assertEquals

class ParserTests {
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
    }

    @Test
    fun whenParsingNonExistingAction_shouldThrowException() {
        val actionsJsonString = NON_ACTION_JSON

        val parser = Parser();

        assertThrows<NoSuchActionException> {
            val actualActions: List<Action> = parser.fromString(actionsJsonString)

        }
    }
}
