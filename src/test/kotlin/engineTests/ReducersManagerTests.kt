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
    fun whenReducersManagerValidate_shouldCallAllValidationReducers() {
        val duplicator1 = mockk<ValidationReducer>()
        val duplicator2 = mockk<ValidationReducer>()

        val gameData = mockk<GameData>()
        val actions = listOf(mockk<ActionData>())

        every { duplicator1.validate(gameData, actions) } returns listOf()
        every { duplicator2.validate(gameData, actions) } returns listOf()

        val list = listOf(duplicator1, duplicator2)

        val manager = ReducerManager(list)

        manager.validateState(gameData, actions)

        list.forEach { it -> verify { it.validate(gameData, actions) } }
    }
}