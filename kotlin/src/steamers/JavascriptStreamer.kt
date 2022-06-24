package steamers

import java.io.File

const val JAVASCRIPT_INTERPRETER = "node "
val JAVASCRIPT_STREAMER_PATH = JAVASCRIPT_INTERPRETER + File("resources/callers/JavascriptCaller.js").absolutePath

class JavascriptStreamer(override val streamerPath: String, override val side: Int) : GameStreamerInterface {
    private var process: Process

    init {
        val command = "$JAVASCRIPT_STREAMER_PATH ${File(streamerPath).absolutePath} $side"
        this.process = Runtime.getRuntime().exec(command)
    }

    override fun getProcess(): Process {
        return process;
    }
}