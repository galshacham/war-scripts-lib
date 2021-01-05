package main

import main.objects.Castle
import main.objects.MapData
import main.objects.actions.Action

class Engine(var mapData: MapData, var castles: List<Castle>) {

    fun isUp(): Boolean = mapData.players.any { player -> castles.all { it.side == player } }

    fun updateData(actions: List<Action>) {
        actions.forEach { it.validate(this) }
        actions.forEach { it.apply(this) }
    }
}