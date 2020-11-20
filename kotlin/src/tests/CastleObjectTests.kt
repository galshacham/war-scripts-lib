package tests

import io.mockk.every
import io.mockk.mockk
import main.objects.Castle
import main.enums.SoldierType
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
        assertEquals(SoldierType.MELEE, castle.soldierType)
    }

    @Test
    fun whenCastleChangesCreationToggle_shouldChangeCreationToggle() {
        castle.changeSoldierType(SoldierType.RANGED);
        assertEquals(SoldierType.RANGED, castle.soldierType)
    }
}

