package tests

import junit.framework.Assert.assertEquals
import main.GameJsonParser
import main.exceptions.ArgumentsException
import main.exceptions.WrongFileFormatException
import main.main.GameManager
import org.junit.Test

class GameManagerTests {

    @Test(expected = WrongFileFormatException::class)
    fun whenGameSettingsFileIsntJson_shouldThrowException() {
        val gameManager = GameManager("notJsonGameSettings.xml")
    }

    @Test
    fun whenGameCreatedWithoutFile_shouldCreateWithDefaultPath() {
        val gameManager = GameManager()

        assertEquals(this::class.java.classLoader.getResource("default.json").readText(), gameManager.gameState)


    }

    @Test (expected = ArgumentsException::class)
    fun whenInitializingGameManagerWithWrongArguments_shouldThrowArgumentsException() {
        val gameManager = GameManager()
    }
}