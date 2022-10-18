package engineTests.rulesTests.reducersTests

import actionsData.Action
import com.github.stefanbirkner.systemlambda.SystemLambda
import drivers.ActionsDrivers
import drivers.GameDriver
import drivers.ObjectsDriver
import drivers.TestConstants
import objectsData.Loc
import objectsData.Soldier
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import rules.reducers.MoveReducerOld
import java.lang.NullPointerException
import kotlin.test.assertEquals

// After many thoughts, we must use mocks!
// The reason to use mocks is that you must not use data classes in unit tests!
// If we use data classes, then we are forcing them to be the last class
// For instance, if we want to expand Soldier (just as it will go with range, melee...) but all of them use this
// Then we must test the Soldier, but Soldier is abstract
// --- Note so sure about it now

class MoveReducerTests {
    private val moveReducer = MoveReducerOld()
    private val soldier = ObjectsDriver.aMeleeSoldier(id = TestConstants.MELEE_SOLDIER_ID_1, loc = Loc(3, 3)) as Soldier
    private val closeLocationToSoldier = Loc(soldier.loc.col, soldier.loc.row + soldier.speed)
    private val validAction = ActionsDrivers.aMoveAction(activatorId = TestConstants.MELEE_SOLDIER_ID_1, newLoc = closeLocationToSoldier)
    private val farLocationFromSoldier = Loc(soldier.loc.col, soldier.loc.row + 2 * soldier.speed)
    private val invalidAction = ActionsDrivers.aMoveAction(activatorId = TestConstants.MELEE_SOLDIER_ID_1, newLoc = farLocationFromSoldier)
    private val game = GameDriver.aGame(soldier)

    @Test
    fun `WHEN move action is legal since its under max speed SHOULD not filter action`() {
        val expectedActions = listOf(validAction)

        val actionsToFilter = listOf(validAction)

        val filteredActions = moveReducer.validate(game, actionsToFilter)

        assertEquals(expectedActions, filteredActions)
    }

    @Test
    fun `WHEN move action is illegal duo to max speed SHOULD remove action`() {
        val expectedActions = listOf<Action>()

        val actions = listOf(invalidAction)

        val filteredActions = moveReducer.validate(game, actions)

        assertEquals(expectedActions, filteredActions)
    }

    @Test
    fun `WHEN action is invalid SHOULD log ignore message`() {
        val actions = listOf(invalidAction)

        val output = SystemLambda.tapSystemOut { moveReducer.validate(game, actions) }

        assertEquals(
            """Error: ignored action from activator: [${invalidAction.activatorId}], can not move more than ${soldier.speed} steps at a time""".trim(),
            output.trim()
        )
    }

    @Test
    fun `WHEN action is valid SHOULD not log ignore message`() {
        val actions = listOf(validAction)

        val output = SystemLambda.tapSystemOut { moveReducer.validate(game, actions) }

        assertEquals(
            "".trim(),
            output.trim()
        )
    }


    @Test
    fun `WHEN updating state with MoveActionReducer SHOULD set new to new game state`() {
        val currentSoldiersLocation = Loc(3, 3)
        val newSoldiersLocation = Loc(1, 5)
        val currentGame = GameDriver.aGame(
            ObjectsDriver.aMeleeSoldier(id = TestConstants.MELEE_SOLDIER_ID_1, loc = currentSoldiersLocation) as Soldier,
            ObjectsDriver.aMeleeSoldier(id = TestConstants.RANGED_SOLDIER_ID_1, loc = currentSoldiersLocation) as Soldier
        )

        val expectedGame = GameDriver.aGame(
            ObjectsDriver.aMeleeSoldier(id = TestConstants.MELEE_SOLDIER_ID_1, loc = newSoldiersLocation) as Soldier,
            ObjectsDriver.aMeleeSoldier(id = TestConstants.RANGED_SOLDIER_ID_1, loc = newSoldiersLocation) as Soldier
        )

        val actions = listOf(
            ActionsDrivers.aMoveAction(activatorId = TestConstants.MELEE_SOLDIER_ID_1, newLoc = newSoldiersLocation),
            ActionsDrivers.aMoveAction(activatorId = TestConstants.RANGED_SOLDIER_ID_1, newLoc = newSoldiersLocation)
        )

        moveReducer.update(currentGame, actions)

        assertEquals(expectedGame, currentGame)
    }

    // This case shouldn't happen in any case!
    // That is why when it does happen, the game should crash
    @Test
    fun `GIVEN invalid state (activatorId which doesn't exist) WHEN updating state with MoveActionReducer SHOULD throw exception`() {
        val currentGame = GameDriver.aGame(
            ObjectsDriver.aMeleeSoldier(id = TestConstants.MELEE_SOLDIER_ID_1) as Soldier,
        )

        val actions = listOf(
            ActionsDrivers.aMoveAction(activatorId = TestConstants.MELEE_SOLDIER_ID_2),
        )

        assertThrows<NullPointerException> {
            moveReducer.update(currentGame, actions)
        }
    }
}