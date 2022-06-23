package steamers

import Utils.Companion.getFileSuffix
import exceptions.NoStramerException


class GameStreamerFactory {
    fun createStreamer(codePath: String): GameStreamerInterface {
        val suffix = getFileSuffix(codePath)
        return when (suffix) {
            "py" -> PythonStreamer(codePath)
            "js" -> JavascriptStreamer(codePath)
            else -> throw NoStramerException("streamer of type ($suffix) do not exist, it can be added on streamers.properties file")
        }
    }
}