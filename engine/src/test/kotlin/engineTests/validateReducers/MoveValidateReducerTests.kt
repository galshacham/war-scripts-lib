package engineTests.validateReducers

import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import actionsData.Action
import actionsData.MoveAction
import reducers.validatingReducers.MoveValidateReducer
import objectsData.Game
import objectsData.Loc
import objectsData.Soldier
import io.mockk.every
import io.mockk.mockk
import objectsData.Castle
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MoveValidateReducerTests {
    private val moveValidationReducer = MoveValidateReducer()
    private val soldierId = 1
    private lateinit var game: Game
    private lateinit var soldier: Soldier

    @BeforeEach
    fun init() {
        game = mockk()
        soldier = mockk()

        every { soldier.id } returns soldierId
        every { soldier.speed } returns 4
        every { soldier.loc } returns Loc(7, 7)
        every { game.objects } returns mutableMapOf(Pair(soldierId, soldier))
    }

    @Test
    fun `WHEN move action is legal since its under max speed SHOULD not filter action`() {
        val validAction = MoveAction(soldierId, Loc(5, 5), 1)
        val expectedActions = listOf(validAction)

        val actions = listOf(validAction)

        val filteredActions = moveValidationReducer.validate(game, actions)

        assertEquals(expectedActions, filteredActions)
    }

    @Test
    fun `WHEN move action is illegal duo to max speed SHOULD remove action`() {
        val invalidAction = MoveAction(soldierId, Loc(4, 5), 1)
        val expectedActions = listOf<Action>()

        val actions = listOf(invalidAction)

        val filteredActions = moveValidationReducer.validate(game, actions)

        assertEquals(expectedActions, filteredActions)
    }

    @Test
    fun `WHEN action is invalid SHOULD log ignore message`() {
        val invalidAction = MoveAction(soldierId, Loc(999, 999), 1)
        val expectedActions = listOf<Action>()

        val actions = listOf(invalidAction)

        val output = tapSystemOut { moveValidationReducer.validate(game, actions) }

        assertEquals(
            """Error: ignored action from activator: [${invalidAction.activatorId}], can not move more than ${soldier.speed} steps at a time""".trim(),
            output.trim()
        )
    }

    @Test
    fun `WHEN action is valid SHOULD not log ignore message`() {
        val invalidAction = MoveAction(soldierId, Loc(6, 6), 1)

        val actions = listOf(invalidAction)

        val output = tapSystemOut { moveValidationReducer.validate(game, actions) }

        assertEquals(
            "".trim(),
            output.trim()
        )
    }
}