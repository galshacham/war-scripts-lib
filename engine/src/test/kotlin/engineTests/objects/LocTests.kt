package engineTests.objects

import engine.objectsData.Loc
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class LocTests {
    @Test
    fun `WHEN loc is in range SHOULD return true`() {
        val loc1 = Loc(0, 0)
        val loc2 = Loc(1, 2)

        assertTrue { loc1.inRange(loc2, 3) }
    }

    @Test
    fun `WHEN loc is not in range SHOULD return false`() {
        val loc1 = Loc(0, 0)
        val loc2 = Loc(2, 2)

        assertFalse {loc1.inRange(loc2, 3) }
    }
}