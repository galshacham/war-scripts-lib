package engineTests

import engine.reducers.ReducerManager
import engine.actionsData.Action
import engine.reducers.activationReducers.IActivationReducer
import engine.objectsData.Game
import engine.objectsData.GameData
import engine.objectsData.GameObject
import engine.reducers.finaleReducers.IFinaleReducer
import engine.reducers.validationReducers.IValidationReducer
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class ReducersManagerTests {
    @Test
    fun `WHEN reducer manager validate SHOULD call all validation reducers`() {
        val validationReducer1 = mockk<IValidationReducer>()
        val validationReducer2 = mockk<IValidationReducer>()

        val game = mockk<Game>()
        val actions = listOf(mockk<Action>())

        every { validationReducer1.validate(game, actions) } returns listOf()
        every { validationReducer2.validate(game, actions) } returns listOf()

        val validationReducers = listOf(validationReducer1, validationReducer2)

        val manager = ReducerManager(validationReducers, listOf(), listOf())
        manager.validateState(game, actions)

        validationReducers.forEach { it -> verify { it.validate(game, actions) } }
    }

    @Test
    fun `WHEN reducer manager updates state SHOULD call all actions reducers`() {
        val game = mockk<Game>()
        val activationReducer1 = mockk<IActivationReducer<Action>>()
        val activationReducer2 = mockk<IActivationReducer<Action>>()
        val actions = listOf<Action>()

        val activationReducers = listOf(activationReducer1, activationReducer2)

        every { activationReducer1.update(game, actions) } returns Unit
        every { activationReducer2.update(game, actions) } returns Unit


        val manager = ReducerManager(listOf(), activationReducers, listOf())
        manager.updateState(game, actions)

        activationReducers.forEach { verify { it.update(game, actions) } }
    }

    @Test
    fun `WHEN reducer manager finales SHOULD call all finales reducers`() {
        val game = mockk<Game>()

        val finaleReducer1 = mockk<IFinaleReducer>()
        val finaleReducer2 = mockk<IFinaleReducer>()
        val finaleReducers = listOf(finaleReducer1, finaleReducer2)

        every { finaleReducer1.finaleState(game) } returns false
        every { finaleReducer2.finaleState(game) } returns false

        val manager = ReducerManager(listOf(), listOf(), finaleReducers)
        manager.finaleState(game)

        finaleReducers.forEach { verify { it.finaleState(game) } }
    }

//    @Test
//    fun `WHEN deep copying game SHOULD create new identical game`() {
//        val manager = ReducerManager(listOf(), listOf(), listOf())
//        val game = mockk<Game>()
//
//        val object1 = mockk<GameObject>()
//        val object2 = mockk<GameObject>()
//        val objects = listOf(object1, object2)
//
//        val gameData = mockk<GameData>()
//
//        every { game.gameData } returns gameData
//        every { gameData.copy() } returns mockk()
//        every { object1.copy() } returns mockk()
//        every { object2.copy() } returns mockk()
//        every { game.objects } returns objects
//
//        val cloned = manager.deepCopy(game)
//
//        objects.forEach { verify { it.copy() } }
//        verify { gameData.copy() }
//    }
}