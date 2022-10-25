package botRunner

interface IBotRunner {
    fun doTurn(currentGameStateJson: ByteArray): ByteArray
}