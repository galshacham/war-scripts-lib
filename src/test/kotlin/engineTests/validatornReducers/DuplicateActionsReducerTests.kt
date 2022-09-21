package engineTests.validatornReducers

import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import engine.actionsData.Action
import engine.objectsData.Game
import engine.validationReducers.DuplicateActionsReducer
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DuplicateActionsReducerTests {
    private val duplicateActionsReducer = DuplicateActionsReducer()
    private lateinit var game: Game
    private lateinit var action1: Action
    private lateinit var action2: Action
    private lateinit var action3: Action

    @BeforeEach
    fun init() {
        game = mockk()
        action1 = mockk()
        action2 = mockk()
        action3 = mockk()

        every { action1.activatorId } returns "duplicated"
        every { action2.activatorId } returns "unique"
        every { action3.activatorId } returns "duplicated"
    }

    @Test
    fun `WHEN there are two actions invoked from the same object SHOULD filter the second one`() {

        val expectedActions = listOf(action1, action2)

        val actions = listOf(action1, action2, action3)

        val filteredActions = duplicateActionsReducer.validate(game, actions)

        assertEquals(expectedActions, filteredActions)
    }

    @Test
    fun `WHEN there are two actions invoked from the same object SHOULD log ignore message`() {

        val actions = listOf(action1, action3, action3)

        val output = tapSystemOut { duplicateActionsReducer.validate(game, actions) }

        assertEquals(
            """
            |Error: ignored action from activator: [${action3.activatorId}], only first action committed
            |Error: ignored action from activator: [${action3.activatorId}], only first action committed
        """.trimMargin(), output.trim()
        )
    }
}