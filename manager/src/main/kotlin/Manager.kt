import botRunner.BotRunnerFactory
import botRunner.IBotRunner
import exceptions.NoArgumentsException

class Manager(val runnerFactory: BotRunnerFactory) {
    val runners = mutableListOf<IBotRunner>()
    fun init(vararg arguments: String) {
        if (arguments.isEmpty()) {
            throw NoArgumentsException()
        }

        arguments.forEachIndexed { i, it ->
            runners.add((runnerFactory.createBotRunner(it, i)))
        }

    }

}