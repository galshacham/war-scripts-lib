package engineTests.rulesTests.postReducerTests

import rules.postRedcuers.TurnsReducer
import objectsData.Game
import objectsData.GameData
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class TurnsReducerTests {
    @Test
    fun `WHEN reducer is called SHOULD add turn`() {
        val turnsReducer = TurnsReducer()

        val game = Game(mutableMapOf(), GameData(50, 20))
        val expectedGame = Game(mutableMapOf(), GameData(50, 21))

        turnsReducer.applyState(game)

        assertEquals(expectedGame, game)
    }
}