package data

@Suppress("ALL")
class FieldShiftAssignmentKotlinTestData <fold text='{...}' expand='true'>{
    var username: String = ""
    var active: Boolean = false

    fun assignFrom(source: FieldShiftAssignmentKotlinTestData) <fold text='{...}' expand='true'>{
        username = source.<fold text='<<' expand='false'>username</fold>
        active = source.<fold text='<<' expand='false'>active</fold>
        child = source.child
    }</fold>

    var child: FieldShiftAssignmentKotlinTestData? = null
}</fold>
