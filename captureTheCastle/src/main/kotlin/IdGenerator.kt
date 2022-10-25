import objectsData.GameObject

object IdGenerator {
    private var currentId: Int = Int.MIN_VALUE

    fun getId(objectsMap: MutableMap<Int, GameObject>): Int {
        if (currentId == Int.MIN_VALUE) {
            currentId = objectsMap.keys.max() + 1
        }

        return currentId++
    }
}