package engineTests.reducerTests.applyingRedcuers

import enums.ObjectTypeEnum
import IdGenerator
import drivers.GameDriver.aGame
import drivers.ObjectsDriver.aCastle
import drivers.ObjectsDriver.aMeleeSoldier
import drivers.ObjectsDriver.aRangedSoldier
import drivers.TestConstants.CASTLE_ID_1
import drivers.TestConstants.CASTLE_ID_2
import drivers.TestConstants.OWNER_ID_1
import drivers.TestConstants.OWNER_ID_2
import rules.postRedcuers.SoldierCreationReducer
import objectsData.*
import io.mockk.every
import io.mockk.mockkObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class SoldierCreationReducerTests {
    private val expectedRanger = 50
    private val expectedMelee = 51
    private val reducer = SoldierCreationReducer()

    @BeforeEach
    fun initStaticMocks() {

        mockkObject(IdGenerator)
        every { IdGenerator.getId(any()) } returns expectedRanger andThen expectedMelee
    }

    @Test
    fun `WHEN reducer is called SHOULD add melee soldier to castles`() {
        val game = aGame(
            aCastle(id = CASTLE_ID_1, owner = OWNER_ID_1, soldierType = ObjectTypeEnum.RANGED, loc = Loc(5, 5)),
            aCastle(id = CASTLE_ID_2, owner = OWNER_ID_2, soldierType = ObjectTypeEnum.MELEE, loc = Loc(6, 6))
        )

        val expectedGame = aGame(
            aCastle(id = CASTLE_ID_1, owner = OWNER_ID_1, soldierType = ObjectTypeEnum.RANGED, loc = Loc(5, 5)),
            aCastle(id = CASTLE_ID_2, owner = OWNER_ID_2, soldierType = ObjectTypeEnum.MELEE, loc = Loc(6, 6)),
            aRangedSoldier(owner = OWNER_ID_1, id = expectedRanger, loc = Loc(5, 5)),
            aMeleeSoldier(owner = OWNER_ID_2, id = expectedMelee, loc = Loc(6, 6))
        )

        reducer.applyState(game)

        assertEquals(expectedGame, game)
    }
}