import exceptions.NoPlayersException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class ManagerTests {
    @Test
    fun `WHEN initiating manager without players SHOULD throw exception`() {
        val message = assertThrows<NoPlayersException> {
            val manager = Manager()
        }.message

        assertEquals(message, "At least one player must be assigned")
    }
}