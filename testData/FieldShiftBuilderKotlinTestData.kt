package data

@Suppress("ALL")
class FieldShiftBuilderKotlinTestData <fold text='{...}' expand='true'>{

    data class Source(val username: String, val active: Boolean)

    class Builder <fold text='{...}' expand='true'>{
        fun username(username: String): Builder = this
        fun active(active: Boolean): Builder = this
        fun child(child: Builder): Builder = this
    }</fold>

    fun build(source: Source): Builder <fold text='{...}' expand='true'>{
        return Builder()
            .username(source.<fold text='<<' expand='false'>username</fold>)
            .active(source.<fold text='<<' expand='false'>active</fold>)
            .child(Builder())
    }</fold>
}</fold>
