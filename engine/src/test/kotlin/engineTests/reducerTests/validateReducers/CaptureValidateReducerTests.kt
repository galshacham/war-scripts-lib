package engineTests.reducerTests.validateReducers

import GameConstants.Companion.LOYAL_AFFECTION_RANGE
import actionsData.CaptureAction
import io.mockk.every
import io.mockk.mockk
import objectsData.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import reducers.validatingReducers.CaptureValidateReducer
import kotlin.test.assertEquals

class CaptureValidateReducerTests {
    private val castleId = 7
    private val soldierId = 8
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
    private val captureValidateReducer = CaptureValidateReducer()

    @BeforeEach
    fun initTests() {
        every { gameMock.objects } returns objects
        every { castle.loc } returns castleLocMock
        every { soldier.loc } returns soldierLocMock
        every { castle.id } returns castleId
        every { captureActionMock.idToCapture } returns castleId
        every { captureActionMock.activatorId } returns soldierId
    }

    @Test
    fun `WHEN soldier affecting loyalty on an existing castle in range SHOULD not filter action`() {
        every { castle.loc.inRange(soldierLocMock, LOYAL_AFFECTION_RANGE) } returns true

        val expectedActions = listOf(captureActionMock)

        val actualActions = captureValidateReducer.validate(gameMock, expectedActions)

        assertEquals(expectedActions, actualActions)
    }

    @Test
    fun `WHEN soldier affecting loyalty on an existing castle out of range SHOULD filter action`() {
        every { castle.loc.inRange(soldierLocMock, LOYAL_AFFECTION_RANGE) } returns false

        val actions = listOf(captureActionMock)

        val actualActions = captureValidateReducer.validate(gameMock, actions)

        assertEquals(listOf(), actualActions)
    }
}