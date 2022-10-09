package engineTests

import IdGenerator
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockkObject
import org.junit.jupiter.api.*
import kotlin.test.assertEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class IdGeneratorTests {
    companion object {
        @JvmStatic
        @BeforeAll
        fun initIdGenerator(): Unit {
            clearAllMocks()
        }
    }

    @Order(1)
    @Test
    fun `WHEN generator gets first id SHOULD return 0`() {
        assertEquals(0, IdGenerator.getId())
    }

    @Order(2)
    @Test
    fun `WHEN generator gets second id SHOULD return 1`() {
        assertEquals(1, IdGenerator.getId())
    }
}
