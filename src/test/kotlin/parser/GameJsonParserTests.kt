//package tests.parser
//
//import objects.Castle
//import objects.Location
//import org.junit.Assert.assertEquals
//import org.junit.Test
//import parser.GameJsonParser
//import tests.FileTestUtils.Companion.getResourceFileText
//
//class GameJsonParserTests {
//    @Test
//    fun givenGameJsonParser_whenGettingMapData_shouldReturnMapData() {
//        val parser = GameJsonParser()
//        val jsonString = getResourceFileText("default.json");
//
//        val game = parser.parseToGameData(jsonString)
//        assertEquals(20, game.mapData.cols)
//        assertEquals(20, game.mapData.rows)
//    }
//
//    @Test
//    fun givenGameSettingsFile_whenCastlesData_shouldReturnCastlesData() {
//        val jsonString = getResourceFileText("default.json")
//        val parser = GameJsonParser()
//
//        val game = parser.parseToGameData(jsonString)
//
//        val expectedCastles = listOf(
//                Castle(0, 0, Location(1, 1), null),
//                Castle(1, 1, Location(19, 19), null),
//                Castle(2, -1, Location(1, 19), null),
//                Castle(3, -1, Location(19, 1), null))
//
//        assertEquals(expectedCastles, game.gameObjects)
//    }
//
//
////
////    @Test
////    fun givenSoldierDataAsJson_whenParsingToSoldierObject_shouldParseTheEnum() {
////        val jsonString = getResourceFileText("gameJsonParser/soldier.json")
////        val parser = GameJsonParser()
////
////        val expectedSoldier = Soldier("1", 1, Location(5, 6), SoldierTypeEnum.MELEE)
////
////        assertEquals(expectedSoldier, parser.gsonParser.fromJson(jsonString, Soldier::class.java))
////    }
//
//}
