package tests

import GameManager
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import main.GameJsonParser
import main.exceptions.ArgumentsException
import objects.Results
import org.junit.Before
import org.junit.Test
import steamers.GameStreamerFactory
import steamers.GameStreamerInterface
import tests.FileTestUtils.Companion.getResourceFileText

class GameManagerTests {
    private val defaultGameState = GameManager::class.java.classLoader.getResource("default.json").readText()
    lateinit var mockFactory: GameStreamerFactory
    lateinit var mockStreamer: GameStreamerInterface
    lateinit var mockParser: GameJsonParser

    @Before
    fun init() {
        mockFactory = mockk()
        mockStreamer = mockk()
        mockParser = mockk()

        every { mockFactory.createStreamer(any(), any()) }.returns(mockStreamer)
        every { mockStreamer.streamerPath }.returns("testResources/DemoBot.mock")
    }

    @Test
    fun whenEngineManagerCreatedWithoutFile_shouldCreateWithDefaultPath() {
        val engineManager = GameManager(arrayOf("oneArgument.mock"), mockFactory, mockParser)

        assertEquals(getResourceFileText("default.json"), engineManager.gameState)
    }

    @Test(expected = ArgumentsException::class)
    fun whenInitializingEngineManagerWithWrongArguments_shouldThrowArgumentsException() {
        val emptyArgs = arrayOf<String>()
        val engineManager = GameManager(emptyArgs, mockFactory, mockParser)
    }

    @Test
    fun whenInitializingEngineManagerWithBotsPaths_shouldAddTwoPlayers() {
        val demoBotPath = "testResources/DemoBot.mock"
        val engineManager = GameManager(arrayOf(demoBotPath, demoBotPath), mockFactory, mockParser)

        assertEquals(listOf(demoBotPath, demoBotPath), engineManager.streamers.map { it -> it.streamerPath })
    }

    @Test
    fun whenFinishedRunningGame_shouldExportToJson() {
        val demoBotPath = "testResources/DemoBot.mock"
        val manager = GameManager(arrayOf(demoBotPath), mockFactory, mockParser);

        val results = manager.runGame();

        val expected = Results(listOf(1), listOf())

        assertEquals(expected, results);
    }


    //
    // This is a shitty test. these tests should be integration and mocking is just pointless here
//    @Test
//    fun whenRunningGame_shouldCallEachBotWithItsStreamer() {
//        val demoBotPath1 = "/DemoBot1.mock1"
//        val demoBotPath2 = "/DemoBot2.mock2"
//        val mockStreamer1 = mockk<GameStreamerInterface>()
//        val mockStreamer2 = mockk<GameStreamerInterface>()
//        val jsonParserMock = mockk<GameJsonParser>()
//
//        every { mockStreamer1.call(defaultGameState, jsonParserMock) } returns listOf()
//        every { mockStreamer2.call(defaultGameState, jsonParserMock) } returns listOf()
//
//        every { mockFactory.createStreamer("mock1", 0) }.returns(mockStreamer1)
//        every { mockFactory.createStreamer("mock2", 1) }.returns(mockStreamer2)
//
//
//        val engineManager = EngineManager(arrayOf(demoBotPath1, demoBotPath2), mockFactory, mockParser)
//
//        engineManager.runGame()
//
//        verify(atLeast = 1) { mockStreamer1.call(defaultGameState, jsonParserMock) }
//        verify(atLeast = 1) { mockStreamer2.call(defaultGameState, jsonParserMock) }
//    }
}