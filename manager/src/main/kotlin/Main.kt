import botRunner.BotRunnerFactory
import java.io.File

fun main(vararg args: String) {
    val manager = Manager(BotRunnerFactory(Runtime.getRuntime()), Engine())
    manager.init(*args)

    manager.runGame(File("""E:\ComputerScience\Projects\war-scripts-lib\manager\src\test\resources\newDefault.json""").readText())
}