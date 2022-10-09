package engineTests.integerations

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
    @Test
    fun `WHEN checking if game is over by  SHOULD return false`() {
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