package engineTests

import IdGenerator
import io.mockk.clearAllMocks
import io.mockk.mockk
import objectsData.GameObject
import org.junit.jupiter.api.*
import kotlin.test.assertEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class IdGeneratorTests {
    @Test
    fun `WHEN initiating generator according to to objects SHOULD start counting from the +1 of the highest Id`() {
        val highest = 60
        val objectsMap = mutableMapOf<Int, GameObject>(
            Pair(1, mockk()),
            Pair(highest, mockk()),
            Pair(3, mockk()),
            Pair(5, mockk()),
        )

        assertEquals(highest + 1, IdGenerator.getId(objectsMap))
        assertEquals(highest + 2, IdGenerator.getId(objectsMap))
        assertEquals(highest + 3, IdGenerator.getId(objectsMap))
        assertEquals(highest + 4, IdGenerator.getId(objectsMap))
    }
}
