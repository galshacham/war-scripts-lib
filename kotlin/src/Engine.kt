import main.objects.MapData
import main.objects.actions.Action
import objects.Castle
import objects.GameObject

const val NATURAL_SIDE = -1

class Engine(var mapData: MapData, var gameObjects: MutableList<GameObject>) {

    fun isUp(): Boolean {
        return true;
        val inTurnsCap = mapData.turn < mapData.maxTurns
        // TODO: i want to keep players which do not have castles
        val allCastles = gameObjects.filter {
            it.javaClass == Castle::class.java
        }

        return !mapData.players.any { player ->
            allCastles.all { it.side == player }
        } && inTurnsCap
    }

    fun updateData(actions: List<Action>) {
        actions.forEach { it.validate(this) }
        actions.forEach { it.apply(this) }

        // My thoughts were if should I return updateState from each gameObject, Or should I just add the value
        // For now, I see no reason to just update the engine itself via the objects, since there is no much use in
        // any other places. In the future, we can change it to return execAction or something like this
        // And then it would do the changing
        gameObjects.map { it }.forEach { it.updateState(this) }
    }
}