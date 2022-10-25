import botRunner.BotRunnerFactory
import finaleValidator.FinaleValidator
import kotlinx.serialization.json.Json
import new.FilterManager
import new.StateManager
import java.io.File
import java.util.*

fun main(vararg args: String) {
    // TODO add engine
    val validator = FinaleValidator(listOf())
    val filterManager = FilterManager()
    val stateManager = StateManager()
    val engine = Engine(filterManager, stateManager, validator)

    val manager = Manager(BotRunnerFactory(Runtime.getRuntime()), engine, JsonHandler(Json))
    manager.init(*args)

    println(Date())
    manager.runGame(File("""E:\ComputerScience\Projects\war-scripts-lib\manager\src\test\resources\simpleGame.json""").readBytes())
    println(Date())
}