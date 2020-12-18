package main

fun main(args: Array<String>) {
    val parser = GameJsonParser()

    print(parser.getGameData().mapData)
}