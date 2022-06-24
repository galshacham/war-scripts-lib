import main.GameJsonParser
import steamers.GameStreamerFactory

fun main(args: Array<String>) {
    val parser = GameJsonParser()
    val streamersFactory = GameStreamerFactory()
    val manager = EngineManager(args, streamersFactory, parser)

    manager.runGame()
}