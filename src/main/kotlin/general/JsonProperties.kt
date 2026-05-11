package general

import com.google.gson.Gson
import java.io.InputStreamReader

object JsonConfig {
    private val DEFAULT_JSON_FILE = "/example.json"

    val get: JsonProps by lazy {
        val fileName = System.getProperty("env_config", DEFAULT_JSON_FILE)

        // Получение потока данных
        val jsonStream = JsonConfig::class.java.getResourceAsStream(fileName)
            ?: throw IllegalStateException("JSON file '$fileName' not found")


        val gson = Gson() // Создаём экземпляр парсера Gson

        // Парсим JSON из потока в объект промежуточного класса JsonConfigData
        val configData = gson.fromJson(
            InputStreamReader(jsonStream),    // InputStreamReader оборачивает поток в читаемый текстовый формат
            JsonConfigData::class.java // JsonConfigData::class.java указывает тип целевого объекта
        )

        JsonProps(
            browserName = configData.browserName,
            browserVersion = configData.browserVersion,
            frontendUrl = configData.frontendUrl,
            backendUrl = configData.backendUrl,
            moonHost = configData.moonHost
        )
    }

    // Основной класс, который возвращается методом get
    data class JsonProps(
        val browserName: String,
        val browserVersion: String,
        val frontendUrl: String,
        val backendUrl: String,
        val moonHost: String
    )

    // Промежуточный класс для парсинга JSON
    private data class JsonConfigData(
        val browserName: String,
        val browserVersion: String,
        val frontendUrl: String,
        val backendUrl: String,
        val moonHost: String
    )
}