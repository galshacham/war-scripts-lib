package engineTests.reducerTests.validateReducers

import GameConstants.Companion.LOYAL_AFFECTION_RANGE
import actionsData.CaptureAction
import drivers.GameDriver
import drivers.actions.MoveActionDriver
import drivers.objects.Soldiers
import io.mockk.every
import io.mockk.mockk
import objectsData.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import reducers.validatingReducers.CaptureValidateReducer
import reducers.validatingReducers.MoveValidateReducer
import kotlin.test.assertEquals

class CaptureValidateReducerTests {
    private val moveValidationReducer = MoveValidateReducer()
    private val soldier = Soldiers.aMeleeSoldier()
    private val game = GameDriver.aGameWithSoldier()

    private val captureValidateReducer = CaptureValidateReducer()

    @BeforeEach
    fun initTests() {
//        every { gameMock.objects } returns objects
//        every { castle.loc } returns castleLocMock
//        every { soldier.loc } returns soldierLocMock
//        every { castle.id } returns castleId
//        every { captureActionMock.idToCapture } returns castleId
//        every { captureActionMock.activatorId } returns soldierId
    }

    @Test
    fun `WHEN soldier affecting loyalty on an existing castle in range SHOULD not filter action`() {
        every { castle.loc.inRange(soldierLocMock, LOYAL_AFFECTION_RANGE) } returns true

        val expectedActions = listOf(captureActionMock)

        val actualActions = captureValidateReducer.validate(game, expectedActions)

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