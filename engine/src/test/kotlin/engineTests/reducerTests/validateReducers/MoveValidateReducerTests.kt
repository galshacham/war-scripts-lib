package engineTests.reducerTests.validateReducers

import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import actionsData.Action
import actionsData.MoveAction
import drivers.GameDriver
import drivers.actions.MoveActionDriver.aMoveAction
import drivers.objects.Soldiers
import reducers.validatingReducers.MoveValidateReducer
import objectsData.Loc
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MoveValidateReducerTests {
    private val moveValidationReducer = MoveValidateReducer()
    private val soldier = Soldiers.aMeleeSoldier()
    private val game = GameDriver.aGameWithSoldier()
    private val closeLocationToSoldier = Loc(soldier.loc.col, soldier.loc.row + soldier.speed)
    private val validAction = aMoveAction(newLoc = closeLocationToSoldier)
    private val farLocationFromSoldier = Loc(soldier.loc.col, soldier.loc.row + 2 * soldier.speed)
    private val invalidAction = aMoveAction(newLoc = farLocationFromSoldier)

    @Test
    fun `WHEN move action is legal since its under max speed SHOULD not filter action`() {
        val expectedActions = listOf(validAction)

        val actionsToFilter = listOf(validAction)

        val filteredActions = moveValidationReducer.validate(game, actionsToFilter)

        assertEquals(expectedActions, filteredActions)
    }

    @Test
    fun `WHEN move action is illegal duo to max speed SHOULD remove action`() {
        val expectedActions = listOf<Action>()

        val actions = listOf(invalidAction)

        val filteredActions = moveValidationReducer.validate(game, actions)

        assertEquals(expectedActions, filteredActions)
    }

    @Test
    fun `WHEN action is invalid SHOULD log ignore message`() {
        val actions = listOf(invalidAction)

        val output = tapSystemOut { moveValidationReducer.validate(game, actions) }

        assertEquals(
            """Error: ignored action from activator: [${invalidAction.activatorId}], can not move more than ${soldier.speed} steps at a time""".trim(),
            output.trim()
        )
    }

    @Test
    fun `WHEN action is valid SHOULD not log ignore message`() {
        val actions = listOf(validAction)

        val output = tapSystemOut { moveValidationReducer.validate(game, actions) }

        assertEquals(
            "".trim(),
            output.trim()
        )
    }
}