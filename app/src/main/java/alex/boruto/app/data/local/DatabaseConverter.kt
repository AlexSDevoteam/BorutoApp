package alex.boruto.app.data.local

import androidx.room.TypeConverter

class DatabaseConverter {
    private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>): String {
        val string = list.joinToString(separator)
        println("converted string of list $string")
        return list.joinToString(separator)
    }

    @TypeConverter
    fun convertStringToList(data: String): List<String> {
        return if (data.isEmpty()) {
            emptyList()
        } else {
            data.split(separator)
        }
    }
}