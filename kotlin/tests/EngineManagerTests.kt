package tests

import EngineManager
import executors.GameExecutorFactory
import executors.GameExecutorInterface
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import main.GameJsonParser
import main.exceptions.ArgumentsException
import org.junit.Before
import org.junit.Test
import tests.FileTestUtils.Companion.getResourceFileText

class EngineManagerTests {
    private val defaultGameState = EngineManager::class.java.classLoader.getResource("default.json").readText()
    lateinit var mockFactory: GameExecutorFactory
    lateinit var mockExecutor: GameExecutorInterface
    lateinit var mockParser: GameJsonParser

    @Before
    fun init() {
        mockFactory = mockk()
        mockExecutor = mockk()
        mockParser = mockk()

        every { mockFactory.createExecutor("oneArgument.mock") }.returns(mockExecutor)
    }

    @Test
    fun whenEngineManagerCreatedWithoutFile_shouldCreateWithDefaultPath() {
        val engineManager = EngineManager(arrayOf("oneArgument.mock"), mockFactory, mockParser)

        assertEquals(getResourceFileText("default.json"), engineManager.gameState)
    }

    @Test(expected = ArgumentsException::class)
    fun whenInitializingEngineManagerWithWrongArguments_shouldThrowArgumentsException() {
        val emptyArgs = arrayOf<String>()
        val engineManager = EngineManager(emptyArgs, mockFactory, mockParser)
    }

//    @Test
//    fun whenInitializingEngineManagerWithBotsPaths_shouldAddToPlayers() {
//        val demoBotPath = "testResources/DemoBot.mock"
//        val engineManager = EngineManager.kt(arrayOf(demoBotPath, demoBotPath), mockFactory)
//
//        assertEquals(listOf(demoBotPath, demoBotPath), engineManager.bots)
//    }


    // This is a shitty test. these tests should be integration and mocking is just pointless here
//    @Test
//    fun whenRunningGame_shouldCallEachBotWithItsExecutor() {
//        val demoBotPath1 = "/DemoBot1.mock1"
//        val demoBotPath2 = "/DemoBot2.mock2"
//        val mockExecutor1 = mockk<GameExecutorInterface>()
//        val mockExecutor2 = mockk<GameExecutorInterface>()
//        val jsonParser = GameJsonParser()
//
//        every { mockExecutor1.callExecutor(defaultGameState, jsonParser) } returns listOf()
//        every { mockExecutor2.callExecutor(defaultGameState, jsonParser) } returns listOf()
//
//        every { mockFactory.createExecutor("mock1") }.returns(mockExecutor1)
//        every { mockFactory.createExecutor("mock2") }.returns(mockExecutor2)
//
//
//        val engineManager = EngineManager(arrayOf(demoBotPath1, demoBotPath2), mockFactory, mockParser)
//
//        engineManager.runGame()
//
//        verify(atLeast = 1) { mockExecutor1.callExecutor(defaultGameState, jsonParser) }
//        verify(atLeast = 1) { mockExecutor2.callExecutor(defaultGameState, jsonParser) }
//    }
}