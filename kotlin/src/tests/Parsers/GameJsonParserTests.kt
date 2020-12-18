package tests.Parsers

import exceptions.WrongFileFormatException
import main.objects.Castle
import main.objects.Location
import main.GameJsonParser
import main.objects.actions.Action
import main.objects.actions.ChangeSoldierTypeAction
import org.junit.Test
import kotlin.test.assertEquals

class GameJsonParserTests {

    @Test(expected = WrongFileFormatException::class)
    fun whenGameSettingsFileIsntJson_shouldThrowException() {
        GameJsonParser("src/tests/resources/notJsonGameSettings.xml")
    }

    @Test
    fun givenGameJsonParser_whenGettingMapData_shouldReturnMapData() {
        val parser = GameJsonParser("src/tests/resources/default.json")

        val game = parser.getGameData()
        assertEquals(20, game.mapData.cols)
        assertEquals(20, game.mapData.rows)
    }

    @Test
    fun givenGameSettingsFile_whenCastlesData_shouldReturnCastlesData() {
        val parser = GameJsonParser("src/tests/resources/default.json")

        val game = parser.getGameData()

        val expectedCastles = listOf(
                Castle(0, "red", Location(1, 1)),
                Castle(1, "blue", Location(19, 19)),
                Castle(2, "natural", Location(1, 19)),
                Castle(3, "natural", Location(19, 1)))

        assertEquals(expectedCastles, game.castles)
    }

    @Test
    fun givenGameStateFile_whenActionData_shouldReturnActionData() {
        val parser = GameJsonParser("src/tests/resources/actionTests.json")

        val game = parser.getGameData()

        val expectedActions = listOf<Action>(
                ChangeSoldierTypeAction(0, "red", 0, "RANGE")
        )

        assertEquals(expectedActions, game.actions)
    }
}