package main.objects

import main.objects.actions.Action

class Game(val mapData: MapData, val castles: List<Castle>, val actions: List<Action>)