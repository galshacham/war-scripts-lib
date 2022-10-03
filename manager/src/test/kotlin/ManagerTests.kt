import exceptions.NoArgumentsException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class ManagerTests {
    @Test
    fun `WHEN initiating manager without players SHOULD throw exception`() {
        val manager = Manager()

        val message = assertThrows<NoArgumentsException> {
            manager.init()
        }.message

        assertEquals(message, "At least one argument must be assigned")
    }
}