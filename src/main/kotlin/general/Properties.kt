package general

import java.util.*

object Config {
    private val DEFAULT_PROP_FILE = "/example.properties"

    // Код выполнится только при первом обращении к get, при последующих обращениях будет возвращаться уже созданный объект Props
    val get: Props by lazy {
        val fileName = System.getProperty("env_config", DEFAULT_PROP_FILE)

        // Создаём новый экземпляр Properties
        val properties = Properties().apply {
            // Получение потока данных
            val stream = Config::class.java.getResourceAsStream(fileName)
                ?: throw IllegalStateException("Properties file '$fileName' not found")
            stream.use { load(it) } //  stream.use — гарантирует автоматическое закрытие потока после использования, load(it) — загружает свойства из потока в объект Properties
        }

        Props(
            browserName = properties.getProperty("browser.name"),
            browserVersion = properties.getProperty("browser.version"),
            frontendUrl = properties.getProperty("frontend.url"),
            backendUrl = properties.getProperty("backend.url"),
            moonHost = properties.getProperty("moon.host")
        )
    }

    data class Props(
        val browserName: String,
        val browserVersion: String,
        val frontendUrl: String,
        val backendUrl: String,
        val moonHost: String,
    )
}