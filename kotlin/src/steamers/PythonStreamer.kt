package steamers

val PYTHON_Streamer_PATH = "HELLO WORLD"

class PythonStreamer(override val codeToStreamPath: String) : GameStreamerInterface {
    override val StreamerPath = PYTHON_Streamer_PATH
}