package tests

import EngineManager
import exceptions.NoExecutorException
import exceutors.GameExecutorFactory
import exceutors.GameExecutorInterface
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import main.exceptions.ArgumentsException
import org.junit.Before
import org.junit.Test

class EngineManagerTests {
    lateinit var mockFactory: GameExecutorFactory
    lateinit var mockExecuter: GameExecutorInterface
    @Before
    fun init() {
        mockFactory = mockk()
        mockExecuter = mockk()

        every {mockFactory.createExecutor("mock")}.returns(mockExecuter)
    }

    @Test
    fun whenEngineManagerCreatedWithoutFile_shouldCreateWithDefaultPath() {
        val engineManager = EngineManager(arrayOf("oneArgument.mock"), mockFactory)

        assertEquals(this::class.java.classLoader.getResource("default.json").readText(), engineManager.gameState)
    }

    @Test(expected = ArgumentsException::class)
    fun whenInitializingEngineManagerWithWrongArguments_shouldThrowArgumentsException() {
        val emptyArgs = arrayOf<String>()
        val engineManager = EngineManager(emptyArgs, mockFactory)
    }


//    @Test
//    fun whenInitializingEngineManagerWithBotsPaths_shouldAddToPlayers() {
//        val demoBotPath = "testResources/DemoBot.kt"
//        val engineManager = EngineManager(arrayOf(demoBotPath, demoBotPath))
//
//        assertEquals(arrayOf(demoBotPath, demoBotPath), engineManager.botPaths)
//    }

//    @Test
//    fun whenInitializingEngineManagerWithRightArguments_shouldStartGame() {

///        val results = engineManager.runGame()
//
//        assertEquals(listOf<String>(), results.winner)
//    }
}