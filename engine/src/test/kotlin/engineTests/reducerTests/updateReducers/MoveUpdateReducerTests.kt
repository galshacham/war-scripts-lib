package engineTests.reducerTests.updateReducers

import actionsData.MoveAction
import drivers.ActionsDrivers
import drivers.GameDriver
import drivers.ObjectsDriver
import drivers.TestConstants
import drivers.TestConstants.MELEE_SOLDIER_ID_1
import drivers.TestConstants.RANGED_SOLDIER_ID_1
import reducers.updateReducers.MoveUpdateReducer
import io.mockk.every
import io.mockk.mockk
import objectsData.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class MoveUpdateReducerTests {
    // After many thoughts, we must use mocks!
    // The reason to use mocks is that you must not use data classes in unit tests!
    // If we use data classes, then we are forcing them to be the last class
    // For instance, if we want to expand Soldier (just as it will go with range, melee...) but all of them use this
    // Then we must test the Soldier, but Soldier is abstract
    // --- Note so sure about it now
    private val meleeSoldier = ObjectsDriver.aMeleeSoldier(id = MELEE_SOLDIER_ID_1, loc = Loc(3, 3)) as Soldier
    private val rangedSoldier = ObjectsDriver.aMeleeSoldier(id = RANGED_SOLDIER_ID_1, loc = Loc(3, 3)) as Soldier
    private val closeLocationToSoldier = Loc(meleeSoldier.loc.col, meleeSoldier.loc.row + meleeSoldier.speed)
    private val game = GameDriver.aGame(meleeSoldier, rangedSoldier)

    private val meleeMoveAction =
        ActionsDrivers.aMoveAction(activatorId = MELEE_SOLDIER_ID_1, newLoc = closeLocationToSoldier)
    private val rangedMoveAction =
        ActionsDrivers.aMoveAction(activatorId = RANGED_SOLDIER_ID_1, newLoc = closeLocationToSoldier)


    private val moveChangeReducer = MoveUpdateReducer()

    @Test
    fun `WHEN updating state with MoveActionReducer SHOULD set new to new game state`() {
        val expectedGame = GameDriver.aGame(meleeSoldier, rangedSoldier)

        val actions = listOf(meleeMoveAction, rangedMoveAction)

        moveChangeReducer.update(game, actions)

        assertEquals(meleeSoldier.loc, closeLocationToSoldier)
        assertEquals(rangedSoldier.loc, closeLocationToSoldier)
    }

    // This case shouldn't happen in any case!
    // That is why when it does happen, the game should crash
    @Test
    fun `GIVEN invalid state (activatorId which doesn't exist) WHEN updating state with MoveActionReducer SHOULD throw exception`() {
        val moveChangeReducer = MoveUpdateReducer()

        val soldier = mockk<Soldier>()

        every { soldier.id } returns 4

        val gameObjects = mutableMapOf<Int, GameObject>(Pair(soldier.id, soldier))

        val game = mockk<Game>()
        every { game.objects } returns gameObjects

        val action = mockk<MoveAction>()
        val actions = listOf(action)

        val noneExistId = 6
        every { action.activatorId } returns noneExistId

        assertThrows<java.lang.NullPointerException> {
            moveChangeReducer.update(game, actions)
        }
    }
}