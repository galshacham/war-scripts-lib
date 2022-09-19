package tests

import java.io.File

class TestConstants {
    companion object {
        val JS_VALID_BOT_PATH: String = File("src/test/resources/botRunner/demoJsCode.js").absolutePath
        val PY_VALID_BOT_PATH: String = File("src/test/resources/botRunner/demoPythonCode.py").absolutePath

        val ACTIONS_JSON: String = File("src/test/resources/parser/actions.json").readText()
        val NON_ACTION_JSON: String = File("src/test/resources/parser/nonActions.json").readText()

        val GAME_JSON_STRING: String = File("src/test/resources/parser/game.json").readText()


        val CASTLE_OBJECT_JSON: String = File("src/test/resources/parser/castle.json").readText()


        val SIMPLE_GAME_STATE_JSON: String = File("src/test/resources/botRunner/simpleGameState.json").readText()
        val SIMPLE_ACTIONS_AFTER_TURN_JSON: String =
            File("src/test/resources/botRunner/simpleExpectedActions.json").readText().removeSpaces()
    }
}

private fun String.removeSpaces(): String {
    return this.filter { !it.isWhitespace() }
}
