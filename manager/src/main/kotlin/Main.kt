import botRunner.BotRunnerFactory
import kotlinx.serialization.json.Json
import java.io.File

fun main(vararg args: String) {
    val manager = Manager(BotRunnerFactory(Runtime.getRuntime()), Engine(), JsonHandler(Json))
    manager.init(*args)

    manager.runGame(File("""E:\ComputerScience\Projects\war-scripts-lib\manager\src\test\resources\newDefault.json""").readText())
}