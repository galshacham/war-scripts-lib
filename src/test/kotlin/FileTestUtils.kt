package tests

class FileTestUtils {

    companion object {
        fun getResourceFileText(fileName: String): String {
            return this::class.java.classLoader.getResource(fileName).readText()
        }
    }
}