package com.example.toyawair.ext

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*


fun String.toLocalDateTime(): LocalDateTime {
    val dateTimeFormatter =
        DateTimeFormatter.ofPattern("MMMM d, yyyy h:mm a", Locale.US)
    LocalDateTime.parse(this, dateTimeFormatter)
    return LocalDateTime.parse(this, dateTimeFormatter)
}
