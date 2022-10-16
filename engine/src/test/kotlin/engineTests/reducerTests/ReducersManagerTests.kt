package engineTests.reducerTests

import actionsData.Action
import rules.ReducerManager
import rules.interfaces.IUpdateReducer
import rules.interfaces.IApplyReducer
import rules.interfaces.IValidateReducer
import objectsData.Game
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class ReducersManagerTests {
    @Test
    fun `WHEN reducer manager validate SHOULD call all validation reducers and use their results on each other`() {
        val validateReducer1 = mockk<IValidateReducer<Action>>()
        val validateReducer2 = mockk<IValidateReducer<Action>>()

        val game = mockk<Game>()
        val actions = listOf(mockk<Action>())
        val actionsAfterFirstValidation = listOf<Action>()


        every { validateReducer1.validate(game, actions) } returns actionsAfterFirstValidation
        every { validateReducer2.validate(game, actionsAfterFirstValidation) } returns listOf()

        val validateReducers = listOf(validateReducer1, validateReducer2)

        val manager = ReducerManager(validateReducers, listOf(), listOf())
        manager.validateState(game, actions)

        verify { validateReducer1.validate(game, actions) }
        verify { validateReducer2.validate(game, actionsAfterFirstValidation) }
    }

    @Test
    fun `WHEN reducer manager updates state SHOULD call all update reducers`() {
        val game = mockk<Game>()
        val updateReducer1 = mockk<IUpdateReducer<Action>>()
        val updateReducer2 = mockk<IUpdateReducer<Action>>()
        val actions = listOf<Action>()

        val updateReducers = listOf(updateReducer1, updateReducer2)

        every { updateReducer1.update(game, actions) } returns Unit
        every { updateReducer2.update(game, actions) } returns Unit


        val manager = ReducerManager(listOf(), updateReducers, listOf())
        manager.updateState(game, actions)

        updateReducers.forEach { verify { it.update(game, actions) } }
    }

    @Test
    fun `WHEN reducer manager applies SHOULD call all applies reducers`() {
        val game = mockk<Game>()

        val applyReducer1 = mockk<IApplyReducer>()
        val applyReducer2 = mockk<IApplyReducer>()
        val applyReducers = listOf(applyReducer1, applyReducer2)

        every { applyReducer1.applyState(game) } returns Unit
        every { applyReducer2.applyState(game) } returns Unit

        val manager = ReducerManager(listOf(), listOf(), applyReducers)
        manager.applyState(game)

        applyReducers.forEach { verify { it.applyState(game) } }
    }
}