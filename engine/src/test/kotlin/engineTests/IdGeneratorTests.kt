package engineTests

import engine.IdGenerator
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import kotlin.test.assertEquals

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class IdGeneratorTests {

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
