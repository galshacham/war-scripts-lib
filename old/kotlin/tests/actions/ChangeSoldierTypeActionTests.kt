package tests.actions

import Game
import io.mockk.mockk
import main.enums.SoldierTypeEnum
import objects.actions.ChangeSoldierTypeAction
import objects.Castle
import objects.Location
import org.junit.Test
import kotlin.test.assertEquals

class ChangeSoldierTypeActionTests {
    @Test
    fun whenChangingSoliderTypeOfCastleInOurSide_shouldChangeSoldierType() {
        val initialCastle = Castle("1", 1, Location(1, 1))
        val expectedCastle = Castle("1", 1, Location(1, 1))
        expectedCastle.changeSoldierType(SoldierTypeEnum.RANGED)

        val engine = Game(mockk(), mutableListOf(initialCastle))

        val action = ChangeSoldierTypeAction("1", 1, "1", SoldierTypeEnum.RANGED)

        action.apply(engine)

        assertEquals(expectedCastle, engine.gameObjects.first())
    }
}