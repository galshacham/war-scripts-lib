object IdGenerator {
    private var currentId: Int = 0
    fun getId(): Int {
        return currentId++
    }
}