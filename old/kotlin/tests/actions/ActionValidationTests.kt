package tests.actions

import Game
import exceptions.ActionValidationException
import io.mockk.every
import io.mockk.mockk
import main.enums.SoldierTypeEnum
import objects.actions.ChangeSoldierTypeAction
import org.junit.Test

class ActionValidationTests {
    @Test(expected = ActionValidationException::class)
    fun whenActionHasNoPlayer_shouldThrowActionValidationException() {
        val engine = mockk<Game>()
        every { engine.mapData.players } returns (listOf(1, 2, 3))
        val action = ChangeSoldierTypeAction("1", 4, "1", SoldierTypeEnum.MELEE)

        action.validate(engine)
    }
}