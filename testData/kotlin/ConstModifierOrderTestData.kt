package data.kotlin

class ConstModifierOrderTestData {
    companion object {
        <fold text='const' expand='false'>const val </fold>DEFAULT_CONST = 1
        private <fold text='const' expand='false'>const val </fold>PRIVATE_BEFORE = 2
        <fold text='const private' expand='false'>const private val </fold>PRIVATE_AFTER = 3
        <fold text='const' expand='false'>const val </fold>PUBLIC_CONST = DEFAULT_CONST
    }
}
