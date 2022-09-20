package engineTests.activationReducers

import engine.actionsData.Action
import engine.actionsData.MoveAction
import engine.activationReducers.MoveActionReducer
import engine.objectsData.Game
import engine.objectsData.Loc
import engine.objectsData.Soldier
import io.mockk.mockk
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MoveActionReducerTests {
    // Todo, try to do this with mocks tomorrow
    @Test
    fun `when updating state with MoveActionReducer SHOULD set new to new game state`() {
        val moveActionReducer = MoveActionReducer()

        val activatorId1 = "yo"
        val activatorId2 = "ma"

        val game = Game(
            listOf(
                Soldier(activatorId1, Loc(60, 60)),
                Soldier(activatorId2, Loc(6, 6))
            )
        )

        val newLoc1 = Loc(61, 61)
        val newLoc2 = Loc(7, 7)
        val action1 = MoveAction(activatorId1, newLoc1)
        val action2 = MoveAction(activatorId2, newLoc2)
        val actions = listOf(action1, action2)


        val expectedGame = Game(
            listOf(
                Soldier(activatorId1, newLoc1),
                Soldier(activatorId2, newLoc2)
            )
        )

        moveActionReducer.update(game, actions)

        assertEquals(expectedGame, game)
    }
}