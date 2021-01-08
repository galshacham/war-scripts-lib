import kotlin.math.max

class IdGenerator(game: Engine) {
    fun next() = ++currentId

    var currentId: Int

    init {
        val highestCastleId = if (game.castles.isNotEmpty()) game.castles.map { it.id }.max()!! else 0

        val highestSoldiersId = if (game.soldiers.isNotEmpty()) game.soldiers.map { it.id }.max()!! else 0

        currentId = max(highestCastleId, highestSoldiersId)
    }
}
