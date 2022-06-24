package tests

import EngineManager
import steamers.GameStreamerFactory
import steamers.GameStreamerInterface
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
    lateinit var mockFactory: GameStreamerFactory
    lateinit var mockStreamer: GameStreamerInterface
    lateinit var mockParser: GameJsonParser

    @Before
    fun init() {
        mockFactory = mockk()
        mockStreamer = mockk()
        mockParser = mockk()

        every { mockFactory.createStreamer("oneArgument.mock", any()) }.returns(mockStreamer)
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
//    fun whenRunningGame_shouldCallEachBotWithItsStreamer() {
//        val demoBotPath1 = "/DemoBot1.mock1"
//        val demoBotPath2 = "/DemoBot2.mock2"
//        val mockStreamer1 = mockk<GameStreamerInterface>()
//        val mockStreamer2 = mockk<GameStreamerInterface>()
//        val jsonParser = GameJsonParser()
//
//        every { mockStreamer1.callStreamer(defaultGameState, jsonParser) } returns listOf()
//        every { mockStreamer2.callStreamer(defaultGameState, jsonParser) } returns listOf()
//
//        every { mockFactory.createStreamer("mock1") }.returns(mockStreamer1)
//        every { mockFactory.createStreamer("mock2") }.returns(mockStreamer2)
//
//
//        val engineManager = EngineManager(arrayOf(demoBotPath1, demoBotPath2), mockFactory, mockParser)
//
//        engineManager.runGame()
//
//        verify(atLeast = 1) { mockStreamer1.callStreamer(defaultGameState, jsonParser) }
//        verify(atLeast = 1) { mockStreamer2.callStreamer(defaultGameState, jsonParser) }
//    }
}