package engineTests.validatornReducers

import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import engine.actionsData.Action
import engine.actionsData.MoveAction
import engine.objectsData.Game
import engine.objectsData.Loc
import engine.objectsData.Soldier
import engine.reducers.validationReducers.MoveValidationReducer
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MoveValidationReducerTests {
    private val moveValidationReducer = MoveValidationReducer()
    private val soldierId = "id"
    private lateinit var game: Game
    private lateinit var soldier: Soldier

    @BeforeEach
    fun init() {
        game = mockk()
        soldier = mockk()

        every { soldier.id } returns soldierId
        every { soldier.speed } returns 4
        every { soldier.loc } returns Loc(7, 7)
        every { game.objects } returns mapOf(Pair(soldierId, soldier))
    }

    @Test
    fun `WHEN move action is legal since its under max speed SHOULD do nothing`() {
        val validAction = MoveAction(soldierId, Loc(5, 5))
        val expectedActions = listOf(validAction)

        val actions = listOf(validAction)

        val filteredActions = moveValidationReducer.validate(game, actions)

        assertEquals(expectedActions, filteredActions)
    }

    @Test
    fun `WHEN move action is illegal duo to max speed SHOULD remove action`() {
        val invalidAction = MoveAction(soldierId, Loc(4, 5))
        val expectedActions = listOf<Action>()

        val actions = listOf(invalidAction)

        val filteredActions = moveValidationReducer.validate(game, actions)

        assertEquals(expectedActions, filteredActions)
    }

    @Test
    fun `WHEN there are two actions invoked from the same object SHOULD log ignore message`() {
        val invalidAction = MoveAction(soldierId, Loc(4, 5))
        val expectedActions = listOf<Action>()

        val actions = listOf(invalidAction)

        val filteredActions = moveValidationReducer.validate(game, actions)

        assertEquals(expectedActions, filteredActions)

        val output = tapSystemOut { moveValidationReducer.validate(game, actions) }

        assertEquals(
            """
            |Error: ignored action from activator: [${invalidAction.activatorId}], can not move more than ${soldier.speed}
        """.trimMargin(), output.trim()
        )
    }
}