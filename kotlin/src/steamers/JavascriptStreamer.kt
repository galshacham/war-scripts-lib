package steamers

import java.io.File

const val JAVASCRIPT_INTERPRETER = "node "
val JAVASCRIPT_Streamer = JAVASCRIPT_INTERPRETER + File("resources/callers/JavascriptCaller.js").absolutePath

class JavascriptStreamer(override val codeToStreamPath: String) : GameStreamerInterface {
    override val StreamerPath = JAVASCRIPT_Streamer
}