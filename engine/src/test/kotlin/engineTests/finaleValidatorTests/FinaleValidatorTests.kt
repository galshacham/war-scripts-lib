package engineTests.finaleValidatorTests

import finaleValidator.FinaleValidator
import io.mockk.mockk
import org.junit.jupiter.api.Test
import finaleValidator.IFinaleValidator
import io.mockk.every
import io.mockk.verifyAll
import objectsData.Game
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.assertFalse

class FinaleValidatorTests {
    @Test
    fun `GIVEN validator WHEN running validation SHOULD call all validators`() {
        val validator1 = mockk<IFinaleValidator>()
        val validator2 = mockk<IFinaleValidator>()
        val game = mockk<Game>()

        val validator = FinaleValidator(listOf(validator1, validator2))

        every { validator1.validateGameStatus(game) } returns false
        every { validator2.validateGameStatus(game) } returns true

        validator.validateGameOver(game)

        verifyAll {
            validator1.validateGameStatus(game)
            validator2.validateGameStatus(game)
        }
    }

    @Test
    fun `GIVEN validator WHEN running finale validator SHOULD return true when game is finished`() {
        val validator1 = mockk<IFinaleValidator>()
        val validator2 = mockk<IFinaleValidator>()
        val game = mockk<Game>()

        val validator = FinaleValidator(listOf(validator1, validator2))

        every { validator1.validateGameStatus(game) } returns false
        every { validator2.validateGameStatus(game) } returns true

        assertTrue(validator.validateGameOver(game))
    }

    @Test
    fun `GIVEN validator WHEN running finale validator SHOULD return false when game is not finished`() {
        val validator1 = mockk<IFinaleValidator>()
        val validator2 = mockk<IFinaleValidator>()
        val game = mockk<Game>()

        val validator = FinaleValidator(listOf(validator1, validator2))

        every { validator1.validateGameStatus(game) } returns false
        every { validator2.validateGameStatus(game) } returns false

        assertFalse(validator.validateGameOver(game))
    }
}