package engineTests.reducerTests.updateReducers

import GameConstants.Companion.LOYAL_AFFECTION_VALUE
import actionsData.CaptureAction
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import objectsData.Castle
import objectsData.Game
import objectsData.GameObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import reducers.updateReducers.CaptureUpdateReducer
import kotlin.test.assertEquals

class CaptureReducerTests {
    private val castleId = 7
    private val castle = mockk<Castle>()
    private val captureActionMock = mockk<CaptureAction>()
    private val gameMock = mockk<Game>()
    private val objects = mutableMapOf<Int, GameObject>(
        Pair(castleId, castle),
    )
    private val loyaltyValue = 10
    private val loyaltySlot = slot<Int>()

    private val captureValidateReducer = CaptureUpdateReducer()

    @BeforeEach
    fun initTests() {
        loyaltySlot.captured = loyaltyValue
        every { gameMock.objects } returns objects
        every { captureActionMock.idToCapture } returns castleId

        // Use answer here to calc the loyalty value each time instead of returns constant
        every { castle.loyalty } answers { loyaltySlot.captured }
        every { castle.loyalty = capture(loyaltySlot) } returns Unit
    }

    @Test
    fun `WHEN soldier affecting loyalty of castle SHOULD affect castle change castle's loyalty`() {
        val actions = listOf(captureActionMock)

        val expectedLoyalty = loyaltyValue - LOYAL_AFFECTION_VALUE

        captureValidateReducer.update(gameMock, actions)

        assertEquals(expectedLoyalty, loyaltySlot.captured)
    }

    @Test
    fun `WHEN 3 soldiers affecting loyalty of castle SHOULD affect castle change castle's loyalty by 3`() {
        val actions = listOf(captureActionMock, captureActionMock, captureActionMock)

        val expectedLoyalty = loyaltyValue - 3 * LOYAL_AFFECTION_VALUE

        captureValidateReducer.update(gameMock, actions)

        assertEquals(expectedLoyalty, loyaltySlot.captured)
    }
}