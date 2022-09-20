package engineTests

import engine.ReducerManager
import engine.actionsData.ActionData
import engine.objectsData.GameData
import engine.validationReducers.ValidationReducer
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class ReducersManagerTests {
    @Test
    fun `when reducer manager validate SHOULD call all validation reducers`() {
        val validationReducer1 = mockk<ValidationReducer>()
        val validationReducer2 = mockk<ValidationReducer>()

        val gameData = mockk<GameData>()
        val actions = listOf(mockk<ActionData>())

        every { validationReducer1.validate(gameData, actions) } returns listOf()
        every { validationReducer2.validate(gameData, actions) } returns listOf()

        val list = listOf(validationReducer1, validationReducer2)

        val manager = ReducerManager(list)

        manager.validateState(gameData, actions)

        list.forEach { it -> verify { it.validate(gameData, actions) } }
    }

    @Test
    fun `when reducer manager updates state SHOULD call all actions`() {
        val gameData = mockk<GameData>()
        val action1 = mockk<ActionData>()
        val action2 = mockk<ActionData>()
        val action3 = mockk<ActionData>()

        val actions = listOf(action1, action2, action3)

        val list = listOf<ValidationReducer>()

        val manager = ReducerManager(list)

//        manager.updateState(gameData, actions)

    }
}