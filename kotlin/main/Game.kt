package main

import main.objects.Castle
import main.objects.MapData
import main.objects.actions.Action

data class Game(val mapData: MapData, val castles: List<Castle>, val actions: List<Action>) {

    fun isUp(): Boolean = mapData.players.any { player -> castles.all { it.side == player } }

// && this.currentTurn < this.maxTurns


}