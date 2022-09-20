package engineTests.validatornReducers

import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import engine.actionsData.ActionData
import engine.objectsData.GameData
import engine.validationReducers.DuplicateActionsReducer
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assert
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.DuplicateFormatFlagsException
import kotlin.test.assertEquals

class DuplicateActionsReducerTests {
    private val duplicateActionsReducer = DuplicateActionsReducer()
    private lateinit var gameData: GameData
    private lateinit var action1: ActionData
    private lateinit var action2: ActionData
    private lateinit var action3: ActionData

    @BeforeEach
    fun init() {
        gameData = mockk()
        action1 = mockk()
        action2 = mockk()
        action3 = mockk()

        every { action1.activatorId } returns "duplicated"
        every { action2.activatorId } returns "unique"
        every { action3.activatorId } returns "duplicated"
    }

    @Test
    fun `when there are two actions invoked from the same object SHOULD filter the second one`() {

        val expectedActions = listOf(action1, action2)

        val actions = listOf(action1, action2, action3)

        val filteredActions = duplicateActionsReducer.validate(gameData, actions)

        assertEquals(expectedActions, filteredActions)
    }

    @Test
    fun `when there are two actions invoked from the same object SHOULD log ignore message`() {

        val actions = listOf(action1, action3, action3)

        val output = tapSystemOut { duplicateActionsReducer.validate(gameData, actions) }

        assertEquals(
            """
            |Error: ignored action from activator: [${action3.activatorId}], only first action committed
            |Error: ignored action from activator: [${action3.activatorId}], only first action committed
        """.trimMargin(), output.trim()
        )
    }
}