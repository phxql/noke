package de.mkammerer.noke.storage.cassandra

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.util.*

@Table("note")
data class NoteEntity(
        @PrimaryKey
        val id: UUID,
        val title: String,
        val markdown: String,
        val html: String
)