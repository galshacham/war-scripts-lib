package tests

import main.GameJsonParser
import main.objects.Castle
import main.objects.Location
import org.junit.Test
import kotlin.test.assertEquals

class GameJsonParserTests {
    @Test
    fun givenGameJsonParser_whenGettingMapData_shouldReturnMapData() {
        val parser = GameJsonParser()
        val jsonString = this::class.java.classLoader.getResource("default.json").readText()

        val game = parser.parseToGameData(jsonString)
        assertEquals(20, game.mapData.cols)
        assertEquals(20, game.mapData.rows)
    }

    @Test
    fun givenGameSettingsFile_whenCastlesData_shouldReturnCastlesData() {
        val jsonString = this::class.java.classLoader.getResource("default.json").readText()
        val parser = GameJsonParser()

        val game = parser.parseToGameData(jsonString)

        val expectedCastles = listOf(
                Castle(0, 1, Location(1, 1)),
                Castle(1, 2, Location(19, 19)),
                Castle(2, 0, Location(1, 19)),
                Castle(3, 0, Location(19, 1)))

        assertEquals(expectedCastles, game.castles)
    }

//    @Test
//    fun givenGameStateFile_whenActionData_shouldReturnActionData() {
//        val jsonString = this::class.java.classLoader.getResource("actionTests.json").readText()
//        val parser = GameJsonParser()
//
//        val game = parser.parseToGameData(jsonString)
//
//        val expectedActions = listOf<Action>(
//                ChangeSoldierTypeAction(0, "red", 0, "RANGE")
//        )
//        assertEquals(expectedActions, game.actions)
//    }
//
//    @Test(expected = NoSuchActionException::class)
//    fun givenGameStateFile_whenActionDataDoesNotExist_shouldThrowException() {
//        val jsonString = this::class.java.classLoader.getResource("testResources/notARealActionTests.json").readText()
//
//        GameJsonParser().parseToGameData(jsonString).actions
//    }
}