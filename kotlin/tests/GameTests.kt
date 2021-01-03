package tests

import io.mockk.mockk
import main.objects.Castle
import main.objects.MapData
import org.junit.Before
import org.junit.Test
import javax.swing.Action

class GameTests {
    @Before
    fun init() {
        val mapData = mockk<MapData>()
        val castles = mockk<List<Castle>>()
        val actions = mockk<List<Action>>()
    }

    @Test
    fun givenGame_whenTwoPlayersArePlaying_shouldReturnTrueIsUp() {
//        val game = Game()

    }
}