package main

import EngineManager
import executors.GameExecutorFactory

fun main(args: Array<String>) {
    val parser = GameJsonParser()
    val executorsFactory = GameExecutorFactory()
    val manager = EngineManager(args, executorsFactory, parser)

    manager.runGame()
}