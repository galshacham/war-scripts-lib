package exceutors

interface GameExecutorInterface {
    val executorPath: String
    val codeToExecutePath: String

    fun callExecutor(gameState: String): String {

        return gameState
    }
}