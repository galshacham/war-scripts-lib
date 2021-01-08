import main.objects.Castle
import main.objects.MapData
import main.objects.actions.Action
import objects.Soldier

val NATURAL_SIDE = -1
val TURNS_TO_CREATE_SOLDIER = 5

class Engine(var mapData: MapData, var castles: List<Castle>, var soldiers: List<Soldier>) {

    fun isUp(): Boolean = !mapData.players.any { player -> castles.all { it.side == player } } && mapData.turn < mapData.maxTurns

    fun updateData(actions: List<Action>, soldierFactory: SoldierFactory) {
        actions.forEach { it.validate(this) }
        actions.forEach { it.apply(this) }

        for (castle in castles) {
            if (castle.side != NATURAL_SIDE && mapData.turn % TURNS_TO_CREATE_SOLDIER == 0) {
                castle.createSoldier(soldierFactory)
            }
        }
    }
}