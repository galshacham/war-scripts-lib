import steamers.GameStreamerFactory
import main.GameJsonParser

fun main(args: Array<String>) {
    val parser = GameJsonParser()
    val StreamersFactory = GameStreamerFactory()
    val manager = EngineManager(args, StreamersFactory, parser)

    manager.runGame()
}