package engineTests.reducerTests.applyingRedcuers

import enums.ObjectTypeEnum
import IdGenerator
import reducers.applyingReducers.SoldierCreationReducer
import objectsData.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class SoldierCreationReducerTests {
    private val expectedSoldierId = 60

    @BeforeEach
    fun initStaticMocks() {

        mockkObject(IdGenerator)
        every { IdGenerator.getId(any()) } returns expectedSoldierId
    }

    @Test
    fun `GIVEN a correct state to add soldier WHEN reducer is called SHOULD add ranged soldier to castles`() {
        val reducer = SoldierCreationReducer()
        val game = mockk<Game>()

        val castleId = 1
        val owner = 1

        val castleLocation = Loc(2, 2)
        val castle = Castle(castleId, castleLocation, ObjectTypeEnum.RANGED, owner, 10)
        val objects = mutableMapOf<Int, GameObject>(Pair(castleId, castle))

        every { game.objects } returns objects

        val rangedSoldier = RangedSoldier(expectedSoldierId, castleLocation, owner)

        val expectedObjects = mutableMapOf(
            Pair(castleId, castle),
            Pair(expectedSoldierId, rangedSoldier)
        )

        reducer.applyState(game)

        assertEquals(expectedObjects, game.objects)
    }

    @Test
    fun `GIVEN a correct state to add melee soldier WHEN reducer is called SHOULD add melee soldier to castles`() {
        val reducer = SoldierCreationReducer()
        val game = mockk<Game>()

        val castleId = 1
        val owner = 1

        val castleLocation = Loc(2, 2)
        val castle = Castle(castleId, castleLocation, ObjectTypeEnum.MELEE, owner, 10)
        val objects = mutableMapOf<Int, GameObject>(Pair(castleId, castle))

        every { game.objects } returns objects

        val meleeSoldier = MeleeSoldier(expectedSoldierId, castleLocation, owner)

        val expectedObjects = mutableMapOf(
            Pair(castleId, castle),
            Pair(expectedSoldierId, meleeSoldier)
        )

        reducer.applyState(game)

        assertEquals(expectedObjects, game.objects)
    }
}