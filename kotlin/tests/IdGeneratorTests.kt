package tests

import Engine
import IdGenerator
import io.mockk.every
import io.mockk.mockk
import main.objects.Castle
import objects.Soldier
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class IdGeneratorTests {
    var mockEngine = mockk<Engine>()

    @Before
    fun init() {

        every { mockEngine.castles } returns listOf()
        every { mockEngine.soldiers } returns listOf()
    }

    @Test
    fun givenGame_whenInitializingIdGenerator_shouldAdjustToCurrentId() {
        val idGen = IdGenerator(mockEngine)

        assertEquals(0, idGen.currentId)
    }

    @Test
    fun givenGameWithCastles_whenInitializingIdGenerator_shouldAdjustToCurrentId() {
        val mockCastle1 = mockk<Castle>()
        val mockCastle2 = mockk<Castle>()

        every { mockCastle1.id } returns 0
        every { mockCastle2.id } returns 1

        every { mockEngine.castles } returns listOf(mockCastle1, mockCastle2)

        val idGen = IdGenerator(mockEngine)

        assertEquals(1, idGen.currentId)
    }

    @Test
    fun givenGameWithSoldiers_whenInitializingIdGenerator_shouldAdjustToCurrentId() {
        val mockSoldier1 = mockk<Soldier>()
        val mockSoldier2 = mockk<Soldier>()

        every { mockSoldier1.id } returns 0
        every { mockSoldier2.id } returns 1

        every { mockEngine.soldiers } returns listOf(mockSoldier1, mockSoldier2)

        val idGen = IdGenerator(mockEngine)

        assertEquals(1, idGen.currentId)
    }

    @Test
    fun givenIdGenerator_WhenAskingForNewId_incrementByOne() {
        val idGen = IdGenerator(mockEngine)

        val one = idGen.next()
        val two = idGen.next()
        val three = idGen.next()

        assertEquals(1, one)
        assertEquals(2, two)
        assertEquals(3, three)
    }
}