package engine

import engine.actionsData.Action
import engine.objectsData.Game

interface IEngine {
    fun runTurn(game: Game, actions: List<Action>): Game
}