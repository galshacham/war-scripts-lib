package engineTests

import Engine
import IdGenerator
import actionsData.Action
import actionsData.MoveAction
import enums.SoldierTypeEnum
import io.mockk.*
import objectsData.*
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class EngineTests {

    // This test is integration!
//    @Test
//    fun `WHEN engine parse gameState SHOULD parse to correct game state`() {
//        val engine = spyk<Engine>()
//
//        mockkObject(IdGenerator)
//        every { IdGenerator.getId() } returns 60
//
//        val expectedLoChange = Loc(4, 4)
//        val expectedGame = Game(
//            mutableMapOf(
//                Pair(1, RangedSoldier(1, expectedLoChange)),
//                Pair(2, MeleeSoldier(2, Loc(4, 5))),
//                Pair(3, Castle(3, Loc(6, 7), SoldierTypeEnum.MELEE)),
//            ),
//            GameData(60, 26)
//        )
//
//        val actions = listOf<Action>(
//            MoveAction(1, Loc(3, 4))
//        )
//
//        engine.runTurn(game, actions)
//
//        verify { engine.runTurn(expectedGame, actions) }
//        clearAllMocks()
//    }

    @Test
    fun `WHEN checks if the game is over by turns SHOULD return false`() {
        val engine = Engine()

        val game = Game(
            mutableMapOf(
                Pair(1, RangedSoldier(1, Loc(2, 3))),
                Pair(2, MeleeSoldier(2, Loc(4, 5))),
                Pair(3, Castle(3, Loc(6, 7), SoldierTypeEnum.MELEE)),
            ),
            GameData(60, 25)
        )

        assertFalse(engine.isOver(game))
    }

    @Test
    fun `WHEN checks if the game is over by turns after doTurn SHOULD return true`() {
        val engine = Engine()

        val gameData = GameData(60, 61)

        val game= Game(mutableMapOf(),gameData)

        assertTrue(engine.isOver(game))
    }
}