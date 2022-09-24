package engineTests.activationReducers

import engine.actionsData.MoveAction
import engine.reducers.activationReducers.MoveActionReducer
import engine.objectsData.Game
import engine.objectsData.Loc
import engine.objectsData.Soldier
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class MoveActionReducerTests {
    // After many thoughts, we must use mocks!
    // The reason to use mocks is that you must not use data classes in unit tests!
    // If we use data classes, then we are forcing them to be the last class
    // For instance, if we want to expand Soldier (just as it will go with range, melee...) but all of them use this
    // Then we must test the Soldier, but Soldier is abstract
    @Test
    fun `WHEN updating state with MoveActionReducer SHOULD set new to new game state`() {
        val moveActionReducer = MoveActionReducer()

        val newLoc1 = mockk<Loc>()
        val newLoc2 = mockk<Loc>()

        val activatorId1 = "yo"
        val activatorId2 = "ma"

        val soldier1 = mockk<Soldier>()
        val soldier2 = mockk<Soldier>()

        val loc1 = slot<Loc>()
        val loc2 = slot<Loc>()

        every { soldier1.id } returns activatorId1
        every { soldier2.id } returns activatorId2
        every { soldier1.loc = capture(loc1) } returns Unit
        every { soldier2.loc = capture(loc2) } returns Unit

        val gameObjects = mapOf(Pair(soldier1.id, soldier1), Pair(soldier2.id, soldier2))

        val game = mockk<Game>()
        every { game.objects } returns gameObjects

        val action1 = mockk<MoveAction>()
        val action2 = mockk<MoveAction>()
        val actions = listOf(action1, action2)

        every { action1.newLoc } returns newLoc1
        every { action2.newLoc } returns newLoc2
        every { action1.activatorId } returns activatorId1
        every { action2.activatorId } returns activatorId2

        moveActionReducer.update(game, actions)

        assertEquals(loc1.captured, newLoc1)
        assertEquals(loc2.captured, newLoc2)
    }

    // This case shouldn't happen in any case!
    // That is why when it does happen, the game should crash
    @Test
    fun `GIVEN invalid state (activatorId which doesn't exist) WHEN updating state with MoveActionReducer SHOULD throw exception`() {
        val moveActionReducer = MoveActionReducer()

        val soldier = mockk<Soldier>()

        every { soldier.id } returns "someId"

        val gameObjects = mapOf(Pair(soldier.id, soldier))

        val game = mockk<Game>()
        every { game.objects } returns gameObjects

        val action = mockk<MoveAction>()
        val actions = listOf(action)

        val noneExistId = "noneExistId"
        every { action.activatorId } returns noneExistId

        assertThrows<java.lang.NullPointerException> {
            moveActionReducer.update(game, actions)
        }
    }
}