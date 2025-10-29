package data

@Suppress("ALL")
class FieldShiftBuilderNegativeKotlinTestData <fold text='{...}' expand='true'>{

    data class Source(val info: String, val payload: String)

    private val logger = Logger()
    private val cache = Cache()

    fun log(source: Source) <fold text='{...}' expand='true'>{
        logger.info(source.info)
        cache.put(source.payload)
    }</fold>

    class Logger <fold text='{...}' expand='true'>{
        fun info(message: String) {}
    }</fold>

    class Cache <fold text='{...}' expand='true'>{
        fun put(payload: String) {}
    }</fold>
}</fold>
