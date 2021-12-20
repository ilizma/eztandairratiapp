object Environments {

    data class Environment(
        val baseURL: String = "",
        val audioURL: String = "",
    )

    val release = Environment(
        baseURL = "https://eztanda.com/",
        audioURL = "http://51.68.46.71:9000/eztanda.mp3",
    )
    val debug = Environment(
        baseURL = "https://eztanda.com/",
        audioURL = "http://51.68.46.71:9000/eztanda.mp3",
    )

}