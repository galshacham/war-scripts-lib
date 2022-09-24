package engineTests.integerations

import engine.Engine
import engine.actionsData.Action
import engine.actionsData.MoveAction
import engine.objectsData.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EngineIntegrationTests {

    @Test
    fun `WHEN specific game plan SHOULD change states and reach to the expected state`() {
        val soldierId = "Ola"
        val game = Game(
            mapOf(Pair(soldierId, RangedSoldier(soldierId, Loc(0, 5)))),
            GameData(10, 0)
        )

        val engine = Engine()
        val actions = listOf<Action>(MoveAction(soldierId, Loc(0, 8)))
        val actualGame = engine.runTurn(game, actions)

        val expectedGame = Game(
            mapOf(Pair(soldierId, RangedSoldier(soldierId, Loc(0, 8)))),
            GameData(10, 1)
        )

        assertEquals(expectedGame, actualGame)
    }
}