package engineTests.integerations

import actionsData.Action
import actionsData.MoveAction
import engine.Engine
import objectsData.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EngineIntegrationTests {

    @Test
    fun `WHEN specific game plan SHOULD change states and reach to the expected state`() {
        val soldierId = 1
        val game = Game(
            mutableMapOf(Pair(soldierId, RangedSoldier(soldierId, Loc(0, 5)))),
            GameData(10, 0)
        )

        val engine = Engine()
        val actions = listOf<Action>(MoveAction(soldierId, Loc(0, 8)))
        val actualGame = engine.runTurn(game, actions)

        val expectedGame = Game(
            mutableMapOf(Pair(soldierId, RangedSoldier(soldierId, Loc(0, 8)))),
            GameData(10, 1)
        )

        assertEquals(expectedGame, actualGame)
    }

    @Test
    fun `WHEN specific game plan with two turns SHOULD change states and reach to the expected state`() {
        val soldierId = 2
        val game = Game(
            mutableMapOf(Pair(soldierId, RangedSoldier(soldierId, Loc(0, 5)))),
            GameData(10, 0)
        )

        val engine = Engine()
        val firstTurnActions = listOf<Action>(MoveAction(soldierId, Loc(0, 8)))
        val secondTurnActions = listOf<Action>(MoveAction(soldierId, Loc(3, 8)))

        val firstGameResults = engine.runTurn(game, firstTurnActions)
        val actualGame = engine.runTurn(firstGameResults, secondTurnActions)

        val expectedGame = Game(
            mutableMapOf(Pair(soldierId, RangedSoldier(soldierId, Loc(3, 8)))),
            GameData(10, 2)
        )

        assertEquals(expectedGame, actualGame)
    }
}