import exceptions.NoArgumentsException

class Manager() {
    fun init(vararg arguments: String) {
        if (arguments.isEmpty()) {
            throw NoArgumentsException()
        }
    }

}