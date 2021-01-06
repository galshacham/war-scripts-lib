package tests.actions

import Engine
import exceptions.ActionValidationException
import io.mockk.every
import io.mockk.mockk
import main.objects.Castle
import main.objects.actions.ChangeSoldierTypeAction
import org.junit.Test

class ChangeSoldierTypeActionValidationsTests {
    @Test(expected = ActionValidationException::class)
    fun whenChangingSoldierTypeOfADifferentPlayer_shouldThrowValidationException() {
        val engine = mockk<Engine>()
        every { engine.mapData.players } returns (listOf(1, 2))
        every { engine.castles } returns (listOf(Castle(1, 1, mockk()), Castle(2, 2, mockk())))
        val action = ChangeSoldierTypeAction(1, 2, 1, "MELEE")

        action.validate(engine)
    }
}