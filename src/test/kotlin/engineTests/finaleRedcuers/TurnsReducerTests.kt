package engineTests.finaleRedcuers

import engine.objectsData.Game
import engine.objectsData.GameData
import engine.reducers.finaleReducers.TurnsReducer
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class TurnsReducerTests {
    @Test
    fun `WHEN game turns is over the max turns SHOULD stop game`() {
        val game = mockk<Game>()

        val gameData = mockk<GameData>()

        every { game.gameData } returns gameData
        every { gameData.currentTurn } returns 60
        every { gameData.maxTurns } returns 50

        val turnsReducer = TurnsReducer()

        assertTrue { turnsReducer.finaleState(game) }
    }

    @Test
    fun `WHEN game turns is under the max turns SHOULD do nothing`() {
        val game = mockk<Game>()

        val gameData = mockk<GameData>()

        val currentTurn = slot<Int>()
        val currentTurnValue = 50
        currentTurn.captured = currentTurnValue

        every { game.gameData } returns gameData
        every { gameData.currentTurn } returns currentTurnValue
        every { gameData.currentTurn = capture(currentTurn) } returns Unit
        every { gameData.maxTurns } returns 60

        val turnsReducer = TurnsReducer()

        assertFalse { turnsReducer.finaleState(game) }
        assertEquals(currentTurnValue + 1, currentTurn.captured)
    }
}