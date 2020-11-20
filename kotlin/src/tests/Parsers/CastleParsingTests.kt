package tests.Parsers

import io.mockk.mockk
import main.objects.Castle
import main.objects.Location
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


class CastleParsingTests {
    private lateinit var castle: Castle

    @Before
    fun init() {
        val side = "first"
        val loc = mockk<Location>()

        val id = 0;
        castle = Castle(id, side, loc)
    }

    @Test
    fun whenGettingACastleObject_shouldParseToJsonObject() {

        val expectedString = """{"soldierType" : "MELEE",
                                  |"side" : "first",
                                  |"loc" : {"row":0,"col":0}}""".trimMargin()

        assertEquals(expectedString, castle.parseToJson())
    }

}