interface IEngine {
    // Maybe add init turn so we don't need to parse the json each time
    fun runTurn(gameState: String, botsActions: List<String>): String
    fun isOver(gameState: String): Boolean
}