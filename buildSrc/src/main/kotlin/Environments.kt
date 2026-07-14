object Environments {

    data class Environment(
        val baseURL: String = "",
        val audioURL: String = "",
    )

    val release = Environment(
        baseURL = "https://eztanda.com/",
        audioURL = "https://streaming.eztanda.com/radio",
    )
    val debug = Environment(
        baseURL = "https://eztanda.com/",
        audioURL = "https://streaming.eztanda.com/radio",
    )

}