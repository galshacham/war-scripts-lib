package engineTests

import Engine
import IdGenerator
import actionsData.Action
import actionsData.MoveAction
import enums.SoldierTypeEnum
import io.mockk.*
import objectsData.*
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class EngineTests {
    val BASIC_GAME_STATE = """
        {
          "objects": {
            "1": {
            "type": "ranged",
              "id": 1,
              "loc": {
                "row": 2,
                "col": 3
              },
              "speed": 4
            },
            "2": {
            "type": "melee",
              "id": 2,
              "loc": {
                "row": 4,
                "col": 5
              },
              "speed": 4
            },
            "3": {
            "type": "castle",
              "id": 3,
              "loc": {
                "row": 6,
                "col": 7
              },
            "soldierType": "MELEE"
            }
          },
          "gameData": {
            "maxTurns": 60,
            "currentTurn": 25
          }
        }
        """.trimIndent()
    @Test
    fun `WHEN engine parse gameState SHOULD parse to correct game state`() {
        val engine = spyk<Engine>()

        mockkObject(IdGenerator)
        every { IdGenerator.getId() } returns 60

        val expectedGame = Game(
            mutableMapOf(
                Pair(1, RangedSoldier(1, Loc(2, 3))),
                Pair(2, MeleeSoldier(2, Loc(4, 5))),
                Pair(3, Castle(3, Loc(6, 7), SoldierTypeEnum.MELEE)),
            ),
            GameData(60, 25)
        )

        val expectedActions = listOf<Action>(
            MoveAction(1, Loc(3, 4))
        )

        val actionsString = listOf("""
            [
              {
                "type": "move",
                "activatorId": 1,
                "newLoc": {
                  "row": 3,
                  "col": 4
                }
              }
            ]
        """.trimIndent())

        engine.runTurn(BASIC_GAME_STATE, actionsString)

        verify { engine.runTurn(expectedGame, expectedActions) }
        assertFalse(engine.isOver())
        clearAllMocks()
    }

    @Test
    fun `WHEN checks if the game is over by turns SHOULD return false`() {
        val engine = spyk<Engine>()

        assertFalse(engine.isOver())
    }

    @Test
    fun `WHEN checks if the game is over by turns after doTurn SHOULD return true`() {
        val engine = spyk<Engine>()

        val gameData = GameData(60, 61)

        engine.runTurn(Game(mutableMapOf(),gameData), listOf())

        assertTrue(engine.isOver())
    }
}