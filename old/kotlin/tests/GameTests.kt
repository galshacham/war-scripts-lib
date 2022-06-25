package tests

import Game
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertTrue
import main.objects.MapData
import main.objects.actions.Action
import objects.Castle
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFalse

class GameTests {
    @Test
    fun whenUpdatingGameData_shouldValidateAndApplyActions() {
        val engine = Game(mockk(), mutableListOf())

        val mockAction = mockk<Action>()
        every { mockAction.validate(engine) } returns Unit
        every { mockAction.apply(engine) } returns Unit
        val actions = listOf(mockAction)

        engine.updateData(actions)

        verify(exactly = 1) { actions[0].validate(engine) }
        verify(exactly = 1) { actions[0].apply(engine) }
    }

    lateinit var mapDataMock: MapData
    lateinit var castleMock1: Castle
    lateinit var castleMock2: Castle

    @Before
    fun init() {
        mapDataMock = mockk()
        castleMock1 = mockk()
        castleMock2 = mockk()

        every { mapDataMock.turn } returns 500
        every { mapDataMock.maxTurns } returns 1000
        every { mapDataMock.players } returns listOf(1, 2)
        every { castleMock1.side } returns 1
        every { castleMock2.side } returns 2
    }

    @Test
    fun givenTurnBiggerThanMaxTurn_whenCheckingIfGameIsUp_shouldReturnFalse() {
        every { mapDataMock.turn } returns 1001

        val engine = Game(mapDataMock, mutableListOf(castleMock1, castleMock2))
        assertFalse(engine.isUp())
    }

    @Test
    fun givenOnlyOnePlayerRemains_whenCheckingIfGameIsUp_shouldReturnFalse() {
        every { castleMock2.side } returns 1

        val engine = Game(mapDataMock, mutableListOf(castleMock1, castleMock2))
        assertFalse(engine.isUp())
    }

    @Test
    fun givenMoreThanOnePlayerRemains_whenCheckingIfGameIsUp_shouldReturnTrue() {
        val engine = Game(mapDataMock, mutableListOf(castleMock1, castleMock2))
        assertTrue(engine.isUp())
    }

//    @Test
//    fun givenGameWithTurnToCreateSoldier_whenUpdatingGameData_shouldCreateNewSoldier() {
//        val soldierFactory = mockk<SoldierFactory>()
//        every { soldierFactory.createSoldier(mockk(), mockk(), mockk()) } returns mockk()
//
//        val engine = Engine(mapDataMock, listOf(castleMock1, castleMock2), listOf())
//
//        engine.updateData(listOf(), soldierFactory)
//
//        verify(exactly = 1) { castleMock1.createSoldier(soldierFactory) }
//        verify(exactly = 1) { castleMock2.createSoldier(soldierFactory) }
//    }
}