package integration

import TestConstants
import TestConstants.Companion.SIMPLE_ACTIONS_AFTER_TURN_JSON
import TestConstants.Companion.SIMPLE_GAME_STATE_JSON
import botRunner.BotRunnerFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.Ignore


// Note: Is the integration tests come in to check the integration between Js and Java for instance? or the whole process?
// For now, Lets assume that it checks the Js, since checking all the way is not possible
// Although, I disagree with myself...
class BotRunnerIntegrationTests {
    @Ignore
    @Test
    fun givenAValidBot_whenRunningABotRunnerTurn_shouldRunTurn() {
        val side = 0
        val botPath = TestConstants.JS_VALID_BOT_PATH

        val fact = BotRunnerFactory(Runtime.getRuntime());

        val botRunner = fact.createBotRunner(botPath, side)

        val actualActions = botRunner.doTurn(SIMPLE_GAME_STATE_JSON.toByteArray())

        val expectedActions = SIMPLE_ACTIONS_AFTER_TURN_JSON

        // TODO: parse to json object
        assertEquals(actualActions, expectedActions)
    }
}