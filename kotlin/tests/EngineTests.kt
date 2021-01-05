package tests

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import main.Engine
import main.objects.actions.Action
import org.junit.Test

class EngineTests {
    @Test
    fun whenUpdatingGameData_shouldValidateAndApplyActions() {
        val engine = Engine(mockk(), mockk())

        val mockAction = mockk<Action>()
        every { mockAction.validate(engine) } returns Unit
        every { mockAction.apply(engine) } returns Unit
        val actions = listOf(mockAction)

        engine.updateData(actions)

        verify(exactly = 1) { actions[0].validate(engine) }
        verify(exactly = 1) { actions[0].apply(engine) }
    }
}