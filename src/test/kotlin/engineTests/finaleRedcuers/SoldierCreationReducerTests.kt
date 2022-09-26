package engineTests.finaleRedcuers

import engine.IdGenerator
import engine.objectsData.*
import engine.reducers.finaleReducers.SoldierCreationReducer
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class SoldierCreationReducerTests {
    @Test
    fun `WHEN reducer needs to add soldiers SHOULD add soldiers to castles`() {
        val reducer = SoldierCreationReducer()
        val game = mockk<Game>()

        val castleId = 1

        // TODO: think! where should we mock, and where should we use real data values?
        val castleLocation = Loc(2, 2)
        val castle = Castle(castleId, castleLocation)
        val objects = mutableMapOf<Int, GameObject>(Pair(castleId, castle))

        val expectedSoldierId = 60

        mockkObject(IdGenerator)
        every { game.objects } returns objects
        every { IdGenerator.getId() } returns expectedSoldierId

        val rangedSoldier = RangedSoldier(expectedSoldierId, castleLocation)

        val expectedObjects = mutableMapOf(
            Pair(castleId, castle),
            Pair(expectedSoldierId, rangedSoldier)
        )

        reducer.finaleState(game)

        assertEquals(expectedObjects, game.objects)
    }
}