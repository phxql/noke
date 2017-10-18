package de.mkammerer.noke.business

import java.util.*

data class Note(
        val id: Id,
        val title: String,
        val content: String
) {
    data class Id(val id: UUID) {
        companion object {
            fun parse(input: String): Id = Note.Id(UUID.fromString(input))
        }

        override fun toString(): String = id.toString()
    }
}