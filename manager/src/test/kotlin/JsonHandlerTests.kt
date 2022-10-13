import actionsData.MoveAction
import io.mockk.mockk
import kotlinx.serialization.json.Json
import objectsData.Game
import objectsData.GameData
import objectsData.Loc
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

// Json is not easy to test -_-, thus we will check this functionality here in basic terms
class JsonHandlerTests {

    @Test
    fun `WHEN serializing game object to string SHOULD serialize correctly`() {
        val jsonHandler = JsonHandler(Json)
        val game = Game(
            mutableMapOf(), GameData(6, 5)
        )
        val expectedGame = """{"objects":{},"gameData":{"maxTurns":6,"currentTurn":5}}"""

        val actualGameString = jsonHandler.parseGameToJson(game)

        assertEquals(expectedGame, actualGameString)
    }

    @Test
    fun `WHEN deserializing game string to game object SHOULD deserialize correctly`() {
        val jsonHandler = JsonHandler(Json)
        val gameString = """{"objects":{},"gameData":{"maxTurns":6,"currentTurn":5}}"""

        val expectedGame = Game(
            mutableMapOf(), GameData(6, 5)
        )

        val actualGame = jsonHandler.parseJsonToGame(gameString)

        assertEquals(expectedGame, actualGame)
    }

    @Test
    fun `WHEN deserializing actions string to actions object list SHOULD deserialize correctly`() {
        val jsonHandler = JsonHandler(Json)
        val actionsString = """[{"activatorId":5,"type":"MOVE","newLoc":{"row":6,"col":5}}]"""

        val expectedActions = listOf(MoveAction(5, Loc(5,6), 1))

        val actualActions = jsonHandler.parseJsonToActions(actionsString)

        assertEquals(expectedActions, actualActions)
    }

}