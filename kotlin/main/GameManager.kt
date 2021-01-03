package main

class GameManager {
    val game: Game
    val parser = GameJsonParser()

    constructor(configJson: String) {
        game = parser.parseToGameData(configJson)
    }


}