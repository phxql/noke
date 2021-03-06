package de.mkammerer.noke.business

import java.time.ZonedDateTime
import java.util.*

data class Note(
        val id: Id,
        val title: String,
        val markdown: String,
        val html: String,
        val created: ZonedDateTime
) {
    data class Id(val id: UUID) {
        companion object {
            fun parse(input: String): Id = Note.Id(UUID.fromString(input))
        }

        override fun toString(): String = id.toString()
    }
}