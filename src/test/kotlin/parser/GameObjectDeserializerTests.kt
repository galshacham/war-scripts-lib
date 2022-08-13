//package tests.parser
//
//import com.google.gson.Gson
//import objects.Castle
//import objects.Location
//import org.junit.Test
//import parser.GameObjectDeserializer
//import tests.FileTestUtils
//
//class GameObjectDeserializerTests {
//    val deserializer = GameObjectDeserializer(Gson())
//
//    @Test
//    fun whenDeserializingCastleStringWithoutAction_shouldCreateCastleObject() {
//        val castleJsonString = FileTestUtils.getResourceFileText("./gameJsonDeserializer/castleWithoutAction.json")
//
//        val actual = deserializer.
//
//        val expected = Castle(1, 2, Location(3, 4), null)
//    }
//}