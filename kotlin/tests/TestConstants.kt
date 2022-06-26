package tests

import java.io.File

class TestConstants {
    companion object {
        val JS_VALID_BOT_PATH: String = File("testResources/botRunner/demoJsCode.js").absolutePath
        val PY_VALID_BOT_PATH: String = File("testResources/botRunner/demoPythonCode.py").absolutePath

        val SIMPLE_GAME_STATE_JSON: String = File("testResources/botRunner/simpleGameState.json").readText()
        val SIMPLE_GAME_STATE_AFTER_TURN_JSON: String = File("testResources/botRunner/simpleGameAfterTurnState.json").readText()
    }
}