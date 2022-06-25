package tests.streamers

import exceptions.NoStramerException
import steamers.GameStreamerFactory
//import steamers.PYTHON_Streamer_PATH
import org.junit.Test
import kotlin.test.assertEquals

class GameStreamersFactoryTests {

    @Test(expected = NoStramerException::class)
    fun whenGameStreamerFactoryCreatesNoneExistingStreamer_shouldThrowNoStreamerException() {
        val fact = GameStreamerFactory()

        fact.createStreamer("doesntExist", 1)
    }

//    @Test
//    fun whenGameStreamerCreatesARealStreamer_shouldCreateTheStreamer() {
//        val fact = GameStreamerFactory()
//
//        val demoBotPath = "/demoPythonCode.py"
//        val pyExec = fact.createStreamer(demoBotPath)
//
//        assertEquals(demoBotPath, pyExec.codeToStreamPath)
//        assertEquals(PYTHON_Streamer_PATH, pyExec.streamerPath)
//    }
}