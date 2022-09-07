//package tests
//
//import Parser
//import enums.SoldierTypeEnum
//import exceptions.NoSuchActionException
//import objects.Castle
//import objects.Game
//import comm.outer.MapData
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.assertThrows
//import org.junit.platform.commons.util.StringUtils
//import tests.TestConstants.Companion.ACTIONS_JSON
//import tests.TestConstants.Companion.GAME_JSON_STRING
//import tests.TestConstants.Companion.NON_ACTION_JSON
//import kotlin.test.assertEquals
//
//class ParserTests {
//    @Test
//    fun whenParsingActionsJson_shouldParseToObjects() {
//        val actionsJsonString = ACTIONS_JSON
//
//        val parser = Parser();
//        val actualActions: List<Action> = parser.fromString(actionsJsonString)
//
//        val expectedActions = listOf(
//            ChangeSoldierAction(60, SoldierTypeEnum.RANGED),
//            ChangeSoldierAction(70, SoldierTypeEnum.MELEE)
//        )
//
//        assertEquals(expectedActions, actualActions)
//    }
//
//    @Test
//    fun whenParsingNonExistingAction_shouldThrowException() {
//        val actionsJsonString = NON_ACTION_JSON
//
//        val parser = Parser();
//
//        assertThrows<NoSuchActionException> {
//            val actualActions: List<Action> = parser.fromString(actionsJsonString)
//        }
//    }
//
//    @Test
//    fun whenParsingGameObject_shouldCreateGameJsonString() {
//        val parser = Parser();
//
//        val game = Game(
//            MapData(5, 6, 7, 60, listOf(2, 3)),
//            mutableListOf(
//                Castle(10, 2, Location(4, 4)),
//                Castle(11, 3, Location(4, 5)),
//            )
//        )
//
//        val actualGameJsonString: String = parser.fromObject(game)
//
//        val expectedGameJsonString = GAME_JSON_STRING.replace("\\s+","")
//        StringUtils.
//
//        assertThat()
//        assertEquals(expectedGameJsonString, actualGameJsonString)
//    }
//}
