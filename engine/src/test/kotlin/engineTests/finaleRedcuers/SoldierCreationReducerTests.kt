package engineTests.finaleRedcuers

import enums.SoldierTypeEnum
import IdGenerator
import io.mockk.clearAllMocks
import reducers.finaleReducers.SoldierCreationReducer
import objectsData.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class SoldierCreationReducerTests {
    private val expectedSoldierId = 60

    @BeforeEach
    fun initStaticMocks() {

        mockkObject(IdGenerator)
        every { IdGenerator.getId() } returns expectedSoldierId
    }

    @Test
    fun `GIVEN a correct state to add soldier WHEN reducer is called SHOULD add ranged soldier to castles`() {
        val reducer = SoldierCreationReducer()
        val game = mockk<Game>()

        val castleId = 1

        val castleLocation = Loc(2, 2)
        val castle = Castle(castleId, castleLocation, SoldierTypeEnum.RANGED)
        val objects = mutableMapOf<Int, GameObject>(Pair(castleId, castle))

        every { game.objects } returns objects

        val rangedSoldier = RangedSoldier(expectedSoldierId, castleLocation)

        val expectedObjects = mutableMapOf(
            Pair(castleId, castle),
            Pair(expectedSoldierId, rangedSoldier)
        )

        reducer.finaleState(game)

        assertEquals(expectedObjects, game.objects)
    }

    @Test
    fun `GIVEN a correct state to add melee soldier WHEN reducer is called SHOULD add melee soldier to castles`() {
        val reducer = SoldierCreationReducer()
        val game = mockk<Game>()

        val castleId = 1

        val castleLocation = Loc(2, 2)
        val castle = Castle(castleId, castleLocation, SoldierTypeEnum.MELEE)
        val objects = mutableMapOf<Int, GameObject>(Pair(castleId, castle))

        every { game.objects } returns objects

        val meleeSoldier = MeleeSoldier(expectedSoldierId, castleLocation)

        val expectedObjects = mutableMapOf(
            Pair(castleId, castle),
            Pair(expectedSoldierId, meleeSoldier)
        )

        reducer.finaleState(game)

        assertEquals(expectedObjects, game.objects)
    }
}