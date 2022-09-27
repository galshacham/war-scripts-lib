package engineTests.finaleRedcuers

import bigComm.enums.SoldierTypeEnum
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
    fun `WHEN reducer add ranged soldier SHOULD add ranged soldier to castles`() {
        val reducer = SoldierCreationReducer()
        val game = mockk<Game>()

        val castleId = 1

        val castleLocation = Loc(2, 2)
        val castle = Castle(castleId, castleLocation, SoldierTypeEnum.RANGED)
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

    @Test
    fun `WHEN reducer add melee soldier SHOULD add melee soldier to castles`() {
        val reducer = SoldierCreationReducer()
        val game = mockk<Game>()

        val castleId = 1

        val castleLocation = Loc(2, 2)
        val castle = Castle(castleId, castleLocation, SoldierTypeEnum.MELEE)
        val objects = mutableMapOf<Int, GameObject>(Pair(castleId, castle))

        val expectedSoldierId = 60

        mockkObject(IdGenerator)
        every { game.objects } returns objects
        every { IdGenerator.getId() } returns expectedSoldierId

        val meleeSoldier = MeleeSoldier(expectedSoldierId, castleLocation)

        val expectedObjects = mutableMapOf(
            Pair(castleId, castle),
            Pair(expectedSoldierId, meleeSoldier)
        )

        reducer.finaleState(game)

        assertEquals(expectedObjects, game.objects)
    }
}