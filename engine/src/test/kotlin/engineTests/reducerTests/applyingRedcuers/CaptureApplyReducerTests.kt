package engineTests.reducerTests.applyingRedcuers

import GameConstants.Companion.LOYAL_AFFECTION_RANGE
import actionsData.CaptureAction
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import objectsData.Castle
import objectsData.Game
import objectsData.Loc
import objectsData.Soldier
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import reducers.applyingReducers.CaptureApplyReducer
import kotlin.test.assertEquals

class CaptureReducerTests {
    private val castleId = 7
    private val soldierId = 8
    private val castleOwnerValue = 2
    private val newOwner = 3
    private val ownerSlot = slot<Int>()
    private val soldier = mockk<Soldier>()
    private val castle = mockk<Castle>()
    private val castleLocMock = mockk<Loc>()
    private val soldierLocMock = mockk<Loc>()
    private val captureActionMock = mockk<CaptureAction>()
    private val gameMock = mockk<Game>()
    private val objects = mutableMapOf(
        Pair(castleId, castle),
        Pair(soldierId, soldier),
    )

    @BeforeEach
    fun initTests() {
        ownerSlot.captured = castleOwnerValue
        every { gameMock.objects } returns objects
        every { castle.loc } returns castleLocMock
        every { soldier.loc } returns soldierLocMock
        every { castleLocMock.inRange(any(), LOYAL_AFFECTION_RANGE) } returns true
        every { soldier.owner } returns newOwner
        every { castle.id } returns castleId
        every { castle.loyalty } returns -3
        every { castle.owner } answers { ownerSlot.captured }
        every { castle.owner = capture(ownerSlot) } returns Unit
        every { captureActionMock.idToCapture } returns castleId
        every { captureActionMock.activatorId } returns soldierId
    }

    private val captureApplyReducer = CaptureApplyReducer()

    @Test()
    fun `WHEN castle's loyalty went below zero SHOULD change castle's owner to nearby soldier's owner`() {
        captureApplyReducer.applyState(gameMock)

        assertEquals(newOwner, castle.owner)
    }

    @Test()
    fun `GIVEN soldiers of different owners WHEN castle's loyalty went below zero SHOULD change castle's owner to nearby soldier's owner most populated army`() {
        val smallArmyOwner = 60
        val soldier2 = mockk<Soldier>()
        val soldier3 = mockk<Soldier>()
        every { soldier2.owner } returns newOwner
        every { soldier3.owner } returns smallArmyOwner
        every { soldier2.loc } returns mockk()
        every { soldier3.loc } returns mockk()
        gameMock.objects[2] = soldier2
        gameMock.objects[3] = soldier3

        captureApplyReducer.applyState(gameMock)

        assertEquals(newOwner, castle.owner)
    }

    @Test()
    fun `GIVEN even amount soldiers of different owners WHEN castle's loyalty went below zero SHOULD not change castle owner`() {
        val sameSizeArmyOwner = 60
        val soldier2 = mockk<Soldier>()
        every { soldier2.owner } returns sameSizeArmyOwner
        every { soldier2.loc } returns mockk()
        gameMock.objects[2] = soldier2
//        every { castle.owner } returns originalCastleOwner

        captureApplyReducer.applyState(gameMock)

        assertEquals(castleOwnerValue, castle.owner)
    }
}