package engineTests.finaleValidatorTests.validators

import finaleValidator.validators.TurnsOverValidator
import io.mockk.every
import io.mockk.mockk
import objectsData.Game
import objectsData.GameData
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

// TODO: this needs to be normal dataclasses
class TurnsOverValidatorTests {
    @Test
    fun `WHEN turns are not maxed SHOULD return false`() {
        val turnsOverValidator = TurnsOverValidator()

        val game = mockk<Game>()
        val gameData = mockk<GameData>()
        every { game.gameData } returns gameData
        every { gameData.maxTurns } returns 60
        every { gameData.currentTurn } returns 50

        assertFalse { turnsOverValidator.validateGameStatus(game) }
    }

    @Test
    fun `WHEN turns are over max turns SHOULD return true`() {
        val turnsOverValidator = TurnsOverValidator()

        val game = mockk<Game>()
        val gameData = mockk<GameData>()
        every { game.gameData } returns gameData
        every { gameData.maxTurns } returns 60
        every { gameData.currentTurn } returns 61

        assertTrue { turnsOverValidator.validateGameStatus(game) }
    }
}