package objects

interface GameObject {
    val id: Int
    val owner: Int
    val loc: Location

    fun updateState(game: Game)
}
