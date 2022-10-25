package integrationTests

import Engine
import actionsData.Action
import actionsData.MoveAction
import io.mockk.mockk
import objectsData.Game
import objectsData.GameData
import objectsData.Loc
import objectsData.RangedSoldier
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.Ignore
import kotlin.test.assertEquals

class EngineIntegrationTests {
    // TODO fix
    val engine = mockk<Engine>()

    @Ignore // For now, until I finish changing the reducers
    @Test
    fun `WHEN specific game plan SHOULD change states and reach to the expected state`() {
        val soldierId = 1
        val game = Game(
            mutableMapOf(Pair(soldierId, RangedSoldier(soldierId, Loc(0, 5), 1))),
            GameData(10, 0)
        )

        val actions = listOf<Action>(MoveAction(soldierId, Loc(0, 8), 1))
        val actualGame = engine.runTurn(game, actions)

        val expectedGame = Game(
            mutableMapOf(Pair(soldierId, RangedSoldier(soldierId, Loc(0, 8), 1))),
            GameData(10, 1)
        )

        assertEquals(expectedGame, actualGame)
    }

    @Ignore // For now, until I finish changing the reducers
    @Test
    fun `WHEN specific game plan with two turns SHOULD change states and reach to the expected state`() {
        val soldierId = 2
        val game = Game(
            mutableMapOf(Pair(soldierId, RangedSoldier(soldierId, Loc(0, 5), 1))),
            GameData(10, 0)
        )

        val firstTurnActions = listOf<Action>(MoveAction(soldierId, Loc(0, 8), 1))
        val secondTurnActions = listOf<Action>(MoveAction(soldierId, Loc(3, 8), 1))

        val firstGameResults = engine.runTurn(game, firstTurnActions)
        val actualGame = engine.runTurn(firstGameResults, secondTurnActions)

        val expectedGame = Game(
            mutableMapOf(Pair(soldierId, RangedSoldier(soldierId, Loc(3, 8), 1))),
            GameData(10, 2)
        )

        assertEquals(expectedGame, actualGame)
    }

    @Ignore
    @Test
    fun `GIVEN a game that shouldn't be over WHEN checks checking if game is over SHOULD return false`() {
        val gameData = GameData(60, 25)

        val game = Game(mutableMapOf(), gameData)

        Assertions.assertFalse(engine.isOver(game))
    }

    @Ignore
    @Test
    fun `GIVEN a game that turns are passed max turns WHEN checks checking if game is over SHOULD return true`() {
        val gameData = GameData(1000, 1001)

        val game = Game(mutableMapOf(), gameData)

        Assertions.assertTrue(engine.isOver(game))
    }
}