package engine

object IdGenerator {
    var currentId: Int = 0
    fun getId(): Int {
        return ++currentId
    }
}