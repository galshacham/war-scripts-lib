package tests.actions

import io.mockk.mockk
import main.Engine
import main.enums.SoldierTypeEnum
import main.objects.Castle
import main.objects.Location
import main.objects.actions.ChangeSoldierTypeAction
import org.junit.Test
import kotlin.test.assertEquals

class ChangeSoldierTypeActionTests {
    @Test
    fun whenChangingSoliderTypeOfCastleInOurSide_shouldChangeSoldierType() {
        val initialCastle = Castle(1, 1, Location(1, 1))
        val expectedCastle = Castle(1, 1, Location(1, 1))
        expectedCastle.changeSoldierType(SoldierTypeEnum.RANGED)

        val engine = Engine(mockk(), listOf(initialCastle))

        val action = ChangeSoldierTypeAction(1, 1, 1, SoldierTypeEnum.RANGED.toString())

        action.apply(engine)

        assertEquals(expectedCastle, engine.castles.first())
    }
}