package runner

class Utils {
    companion object {
        fun getFileSuffix(arg: String) = arg.split(".").last()
    }
}