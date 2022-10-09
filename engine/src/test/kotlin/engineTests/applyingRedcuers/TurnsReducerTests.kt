package engineTests.applyingRedcuers

import reducers.applyingReducers.TurnsReducer
import objectsData.Game
import objectsData.GameData
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class TurnsReducerTests {
    @Test
    fun `WHEN reducer is called SHOULD add turn`() {
        val game = mockk<Game>()

        val gameData = mockk<GameData>()

        val currentTurn = slot<Int>()
        val currentTurnValue = 50
        currentTurn.captured = currentTurnValue

        every { game.gameData } returns gameData
        every { gameData.currentTurn } returns currentTurnValue
        every { gameData.currentTurn = capture(currentTurn) } returns Unit

        val turnsReducer = TurnsReducer()

        turnsReducer.applyState(game)

        assertEquals(currentTurnValue + 1, currentTurn.captured)
    }
}