package engineTests.reducerTests.updateReducers

import drivers.ActionsDrivers.aMoveAction
import drivers.GameDriver
import drivers.ObjectsDriver
import drivers.TestConstants.MELEE_SOLDIER_ID_1
import drivers.TestConstants.MELEE_SOLDIER_ID_2
import drivers.TestConstants.RANGED_SOLDIER_ID_1
import objectsData.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import reducers.MoveReducer
import kotlin.test.assertEquals

class MoveReducerTests {
    // After many thoughts, we must use mocks!
    // The reason to use mocks is that you must not use data classes in unit tests!
    // If we use data classes, then we are forcing them to be the last class
    // For instance, if we want to expand Soldier (just as it will go with range, melee...) but all of them use this
    // Then we must test the Soldier, but Soldier is abstract
    // --- Note so sure about it now

    private val moveReducer = MoveReducer()

    @Test
    fun `WHEN updating state with MoveActionReducer SHOULD set new to new game state`() {
        val currentSoldiersLocation = Loc(3, 3)
        val newSoldiersLocation = Loc(1, 5)
        val currentGame = GameDriver.aGame(
            ObjectsDriver.aMeleeSoldier(id = MELEE_SOLDIER_ID_1, loc = currentSoldiersLocation) as Soldier,
            ObjectsDriver.aMeleeSoldier(id = RANGED_SOLDIER_ID_1, loc = currentSoldiersLocation) as Soldier
        )

        val expectedGame = GameDriver.aGame(
            ObjectsDriver.aMeleeSoldier(id = MELEE_SOLDIER_ID_1, loc = newSoldiersLocation) as Soldier,
            ObjectsDriver.aMeleeSoldier(id = RANGED_SOLDIER_ID_1, loc = newSoldiersLocation) as Soldier
        )

        val actions = listOf(
            aMoveAction(activatorId = MELEE_SOLDIER_ID_1, newLoc = newSoldiersLocation),
            aMoveAction(activatorId = RANGED_SOLDIER_ID_1, newLoc = newSoldiersLocation)
        )

        moveReducer.update(currentGame, actions)

        assertEquals(expectedGame, currentGame)
    }

    // This case shouldn't happen in any case!
    // That is why when it does happen, the game should crash
    @Test
    fun `GIVEN invalid state (activatorId which doesn't exist) WHEN updating state with MoveActionReducer SHOULD throw exception`() {
        val currentGame = GameDriver.aGame(
            ObjectsDriver.aMeleeSoldier(id = MELEE_SOLDIER_ID_1) as Soldier,
        )

        val actions = listOf(
            aMoveAction(activatorId = MELEE_SOLDIER_ID_2),
        )

        assertThrows<java.lang.NullPointerException> {
            moveReducer.update(currentGame, actions)
        }
    }
}