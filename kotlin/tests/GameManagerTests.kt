package tests

import junit.framework.Assert.assertEquals
import main.exceptions.ArgumentsException
import main.main.GameManager
import org.junit.Test

class GameManagerTests {

//    @Test(expected = WrongFileFormatException::class)
//    fun whenGameSettingsFileIsntJson_shouldThrowException() {
//        val gameManager = GameManager("notJsonGameSettings.xml")
//    }

    @Test
    fun whenGameCreatedWithoutFile_shouldCreateWithDefaultPath() {
        val gameManager = GameManager(arrayOf("oneArgument"))

        assertEquals(this::class.java.classLoader.getResource("default.json").readText(), gameManager.gameState)


    }

    @Test(expected = ArgumentsException::class)
    fun whenInitializingGameManagerWithWrongArguments_shouldThrowArgumentsException() {
        val emptyArgs = arrayOf<String>()
        val gameManager = GameManager(emptyArgs)
    }

    @Test
    fun whenInitializingGameManagerWithRightArguments_shouldStartGame() {
        val demoBotPath = "testResources/DemoBot.kt"
        val gameManager = GameManager(arrayOf(demoBotPath, demoBotPath))

        val results = gameManager.runGame()

        assertEquals(listOf<String>(), results.winner)
    }
}