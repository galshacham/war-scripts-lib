package engineTests.rulesTests

import enums.ReducerEnum
import io.mockk.every
import io.mockk.mockk
import objectsData.Game
import objectsData.GameObject
import objectsData.Soldier
import org.junit.jupiter.api.Test
//import rules.interfaces.ISoldiersReducer
import rules.reducers.SoldiersReducer
import rules.states.SoldiersState
import rules.states.StateManager
import kotlin.test.assertEquals

class StateManagerTests {
    @Test
    fun `WHEN state manager asked to get soldiers state SHOULD return soldiers state`() {
        val stateManager = StateManager()
        val game = mockk<Game>()
        val reducer = mockk<SoldiersReducer>()

        val soldier1 = mockk<Soldier>()
        val soldier2 = mockk<Soldier>()
        val otherGameObject = mockk<GameObject>()

        every { reducer.getReducerType() } returns ReducerEnum.SOLDIERS
        every { game.objects } returns mutableMapOf(
            Pair(1, soldier1),
            Pair(2, otherGameObject),
            Pair(3, soldier2)
        )


        val state = stateManager.getState(game, reducer)

        val expectedState = SoldiersState(
            mutableMapOf(
                Pair(1, soldier1),
                Pair(3, soldier2)
            )
        )

        assertEquals(expectedState, state)
    }
}