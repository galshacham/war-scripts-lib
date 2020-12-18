package tests

import io.mockk.every
import io.mockk.mockk
import main.enums.SoldierTypeEnum
import main.objects.Castle
import main.objects.Location
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class CastleObjectTests {
    private lateinit var castle: Castle;

    @Before
    fun init() {
        val side = "first"
        val loc = mockk<Location>()
        every { loc.toString() } returns "AAAAAA"

        val id = 0;
        castle = Castle(id, side, loc)
    }

    @Test
    fun whenCreatingACastle_defaultSoldierTypeIsMelee() {
        assertEquals(SoldierTypeEnum.MELEE, castle.soldierType)
    }

    @Test
    fun whenCastleChangesCreationToggle_shouldChangeCreationToggle() {
        castle.changeSoldierType(SoldierTypeEnum.RANGED);
        assertEquals(SoldierTypeEnum.RANGED, castle.soldierType)
    }
}

