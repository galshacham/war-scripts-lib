package engineTests

import engine.ReducerManager
import engine.actionsData.Action
import engine.activationReducers.IActivationReducer
import engine.objectsData.Game
import engine.validationReducers.IValidationReducer
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class ReducersManagerTests {
    @Test
    fun `when reducer manager validate SHOULD call all validation reducers`() {
        val validationReducer1 = mockk<IValidationReducer>()
        val validationReducer2 = mockk<IValidationReducer>()

        val game = mockk<Game>()
        val actions = listOf(mockk<Action>())

        every { validationReducer1.validate(game, actions) } returns listOf()
        every { validationReducer2.validate(game, actions) } returns listOf()

        val validationReducers = listOf(validationReducer1, validationReducer2)
        val activationReducers = listOf(mockk<IActivationReducer<Action>>())

        val manager = ReducerManager(validationReducers, activationReducers)
        manager.validateState(game, actions)

        validationReducers.forEach { it -> verify { it.validate(game, actions) } }
    }

    @Test
    fun `when reducer manager updates state SHOULD call all actions`() {
        val game = mockk<Game>()
        val activationReducer1 = mockk<IActivationReducer<Action>>()
        val activationReducer2 = mockk<IActivationReducer<Action>>()
        val validationReducers = listOf<IValidationReducer>()
        val actions = listOf<Action>()

        val activationReducers = listOf(activationReducer1, activationReducer2)

        val clonedGame = mockk<Game>()
        every { game.copy() } returns clonedGame
        every { activationReducer1.update(clonedGame, actions) } returns Unit
        every { activationReducer2.update(clonedGame, actions) } returns Unit



        val manager = ReducerManager(validationReducers, activationReducers)
        manager.updateState(game, actions)

        activationReducers.forEach { verify { it.update(clonedGame, actions) } }
    }
}