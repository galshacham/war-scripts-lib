package botRunner

interface IBotRunner {
    fun doTurn(currentGameStateJson: String): String
}