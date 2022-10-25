package engineTests.filtersTests

import actionsData.Action
import filters.ActionsFilterManager
import filters.IValidActionPredicate
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import objectsData.Game
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ActionsFilterManagerTests {
    private val predicate1 = mockk<IValidActionPredicate>()
    private val predicate2 = mockk<IValidActionPredicate>()
    private val action1 = mockk<Action>()
    private val action2 = mockk<Action>()
    private val gameMock = mockk<Game>()

    @Test
    fun `GIVEN first invalid action WHEN filtering action SHOULD call only first predicate`() {
        val actions = listOf(action1)
        val actionsFilterManager = ActionsFilterManager(listOf(predicate1, predicate2))
        every { predicate1.isValidAction(gameMock, action1) } returns false
        every { predicate1.logError(action1) } returns "nothing"

        val actualActions = actionsFilterManager.filterActions(gameMock, actions)

        verify { predicate1.isValidAction(gameMock, action1) }
        verify(inverse = true) { predicate2.isValidAction(any(), any()) }
        assertEquals(listOf(), actualActions)
    }

    @Test
    fun `GIVEN valid actions WHEN filtering action SHOULD call all predicates`() {
        val actions = listOf(action1, action2)
        val actionsFilterManager = ActionsFilterManager(listOf(predicate1, predicate2))
        every { predicate1.isValidAction(gameMock, action1) } returns true
        every { predicate2.isValidAction(gameMock, action1) } returns true
        every { predicate1.isValidAction(gameMock, action2) } returns true
        every { predicate2.isValidAction(gameMock, action2) } returns false
        every { predicate2.logError(action2) } returns "nothing"

        val actualActions = actionsFilterManager.filterActions(gameMock, actions)

        verify { predicate1.isValidAction(gameMock, action1) }
        verify { predicate2.isValidAction(gameMock, action1) }
        verify { predicate1.isValidAction(gameMock, action2) }
        verify { predicate2.isValidAction(gameMock, action2) }
        assertEquals(listOf(action1), actualActions)
    }

    @Test
    fun `GIVEN first invalid action WHEN filtering action SHOULD call predicate log message`() {
        val actions = listOf(action1)
        val actionsFilterManager = ActionsFilterManager(listOf(predicate1))
        every { predicate1.isValidAction(gameMock, action1) } returns false
        every { predicate1.logError(action1) } returns "hello world"

        actionsFilterManager.filterActions(gameMock, actions)

        verify { predicate1.isValidAction(gameMock, action1) }
        verify { predicate1.logError(action1) }
    }
}