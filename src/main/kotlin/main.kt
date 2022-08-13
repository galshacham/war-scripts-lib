import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import objects.Castle
import objects.Location

fun main(args: Array<String>) {
    val loc = Castle(1,2, Location(3,4))
    println(loc)

    val string = Json.encodeToString(loc)
    println(string)

    val casted = Json.decodeFromString<Castle>(string)
    println(casted)
//    val parser = parser.GameJsonParser()
//    val streamersFactory = GameStreamerFactory()
//    val manager = GameManager(args, streamersFactory, parser)
//
//    manager.runGame()
    print("QWE")
}