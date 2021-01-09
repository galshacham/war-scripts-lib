package tests.actions

import Engine
import exceptions.ActionValidationException
import io.mockk.every
import io.mockk.mockk
import main.enums.SoldierTypeEnum
import main.objects.actions.ChangeSoldierTypeAction
import objects.Castle
import org.junit.Test

class ChangeSoldierTypeActionValidationsTests {
    @Test(expected = ActionValidationException::class)
    fun whenChangingSoldierTypeOfADifferentPlayer_shouldThrowValidationException() {
        val engine = mockk<Engine>()
        every { engine.mapData.players } returns (listOf(1, 2))
        every { engine.gameObjects } returns (mutableListOf(Castle("1", 1, mockk()), Castle("2", 2, mockk())))
        val action = ChangeSoldierTypeAction("1", 2, "1", SoldierTypeEnum.MELEE)

        action.validate(engine)
    }
}