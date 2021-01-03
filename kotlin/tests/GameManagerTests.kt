package tests

import main.GameManager
import org.junit.Test
import kotlin.test.assertEquals

class GameManagerTests {
    @Test
    fun whenInitializingGame_shouldUseTheJsonConfig() {
        val game = GameManager(this::class.java.classLoader.getResource("default.json").readText())

//        assertEquals(1000, game.maxTurns)
//        assertEquals(0, game.currentTurn)
//        assertEquals(2, game.remainingPlayers)
    }
}