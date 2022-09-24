package engineTests.finaleRedcuers

import engine.objectsData.Game
import engine.objectsData.GameData
import engine.reducers.finaleReducers.MaxTurnsReducer
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertTrue


class MaxTurnsReducerTests {
    @Test
    fun `WHEN game turns is over the max turns SHOULD stop game`() {
        val game = mockk<Game>()

        val gameData = mockk<GameData>()

        every { game.gameData } returns gameData
        every { gameData.currentTurn } returns 60
        every { gameData.maxTurns } returns 50

        val maxTurnsReducer = MaxTurnsReducer()

        assertTrue { maxTurnsReducer.finaleState(game) }
    }

    @Test
    fun `WHEN game turns is under the max turns SHOULD do nothing`() {
        val game = mockk<Game>()

        val gameData = mockk<GameData>()

        every { game.gameData } returns gameData
        every { gameData.currentTurn } returns 50
        every { gameData.maxTurns } returns 60

        val maxTurnsReducer = MaxTurnsReducer()

        assertFalse { maxTurnsReducer.finaleState(game) }
    }
}