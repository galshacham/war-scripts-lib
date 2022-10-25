import objectsData.Game

interface IStatusManager {
    fun validateGameOver(gameState: Game): Boolean
}