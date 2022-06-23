package tests

import main.GameJsonParser
import main.enums.SoldierTypeEnum
import main.exceptions.NoSuchActionException
import main.objects.actions.Action
import main.objects.actions.ChangeSoldierTypeAction
import objects.Castle
import objects.Location
import objects.Soldier
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

class GameJsonParserTests {
    @Test
    fun givenGameJsonParser_whenGettingMapData_shouldReturnMapData() {
        val parser = GameJsonParser()
        val jsonString = getResourceFile("default.json");

        val game = parser.parseToGameData(jsonString)
        assertEquals(20, game.mapData.cols)
        assertEquals(20, game.mapData.rows)
    }

    @Test
    fun givenGameSettingsFile_whenCastlesData_shouldReturnCastlesData() {
        val jsonString = getResourceFile("default.json")
        val parser = GameJsonParser()

        val game = parser.parseToGameData(jsonString)

        val expectedCastles = listOf(
                Castle("0", 0, Location(1, 1)),
                Castle("1", 1, Location(19, 19)),
                Castle("2", -1, Location(1, 19)),
                Castle("3", -1, Location(19, 1)))

        assertEquals(expectedCastles, game.gameObjects)
    }

    @Test
    fun givenActionsJson_whenParsingActionData_shouldReturnActionDataObject() {
        val jsonString = getResourceFile("gameJsonParser/actionTests.json")
        val parser = GameJsonParser()

        val expectedActions = listOf<Action>(
                ChangeSoldierTypeAction("0", 1, "1", SoldierTypeEnum.RANGED)
        )
        assertEquals(expectedActions, parser.parseToActions(jsonString))
    }

    @Test(expected = NoSuchActionException::class)
    fun givenGameStateFile_whenActionDataDoesNotExist_shouldThrowException() {
        val jsonString = getResourceFile("gameJsonParser/notARealActionTests.json")

        GameJsonParser().parseToActions(jsonString)
    }

    @Test
    fun givenSoldierDataAsJson_whenParsingToSoldierObject_shouldParseTheEnum() {
        val jsonString = getResourceFile("gameJsonParser/soldier.json")
        val parser = GameJsonParser()

        val expectedSoldier = Soldier("1", 1, Location(5, 6), SoldierTypeEnum.MELEE)

        assertEquals(expectedSoldier, parser.gsonParser.fromJson(jsonString, Soldier::class.java))
    }

    private fun getResourceFile(fileName: String): String {
        return this::class.java.classLoader.getResource(fileName).readText()
    }
}