package tests.objects

import IdGenerator
import SoldierFactory
import io.mockk.every
import io.mockk.mockk
import main.enums.SoldierTypeEnum
import main.objects.Castle
import objects.Location
import objects.Soldier
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class CastleObjectTests {
    private lateinit var castle: Castle;

    @Before
    fun init() {
        val side = 1
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

    @Test
    fun whenCreatingSoldierViaCastle_shouldCreateSoldierWithStats() {
        val expectedSoldier = Soldier(5, 1, castle.loc, SoldierTypeEnum.MELEE, 4, 2, 2, 1)
        val mockIdGen = mockk<IdGenerator>()
        every { mockIdGen.next() } returns 5

        val factory = SoldierFactory(mockIdGen)

        assertEquals(expectedSoldier, castle.createSoldier(factory))
    }
}

