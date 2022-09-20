package engineTests

import engine.ReducerManager
import engine.actionsData.ActionData
import engine.activationReducers.IActivationReducer
import engine.objectsData.GameData
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

        val gameData = mockk<GameData>()
        val actions = listOf(mockk<ActionData>())

        every { validationReducer1.validate(gameData, actions) } returns listOf()
        every { validationReducer2.validate(gameData, actions) } returns listOf()

        val validationReducers = listOf(validationReducer1, validationReducer2)
        val activationReducers = listOf(mockk<IActivationReducer>())

        val manager = ReducerManager(validationReducers, activationReducers)
        manager.validateState(gameData, actions)

        validationReducers.forEach { it -> verify { it.validate(gameData, actions) } }
    }

    @Test
    fun `when reducer manager updates state SHOULD call all actions`() {
        val gameData = mockk<GameData>()
        val activationReducer1 = mockk<IActivationReducer>()
        val activationReducer2 = mockk<IActivationReducer>()
        val validationReducers = listOf<IValidationReducer>()
        val actions = listOf<ActionData>()

        val activationReducers = listOf(activationReducer1, activationReducer2)

        val clonedGameData = mockk<GameData>()
        every { gameData.copy() } returns clonedGameData
        every { activationReducer1.update(clonedGameData, actions) } returns Unit
        every { activationReducer2.update(clonedGameData, actions) } returns Unit



        val manager = ReducerManager(validationReducers, activationReducers)
        manager.updateState(gameData, actions)

        activationReducers.forEach { verify { it.update(clonedGameData, actions) } }
    }
}