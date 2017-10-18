package de.mkammerer.noke.storage.cassandra

import de.mkammerer.noke.business.Note
import de.mkammerer.noke.business.NoteRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.ZoneId
import java.time.ZonedDateTime

@Repository
class CassandraNoteRepository(
        private val repository: ReactiveCassandraNoteRepository
) : NoteRepository {
    override fun listAll(): Flux<Note> {
        return repository.findAll()
                .map { fromEntity(it) }
                .sort(Comparator.comparing { note: Note -> note.created }.reversed())
    }

    override fun findById(id: Note.Id): Mono<Note> {
        return repository.findById(id.id)
                .map { fromEntity(it) }
    }

    override fun add(note: Note): Mono<Note> {
        return repository.save(toEntity(note))
                .map { note }
    }

    override fun delete(id: Note.Id): Mono<Boolean> {
        return repository.deleteById(id.id)
                .map { true }
                .defaultIfEmpty(true)
    }

    override fun update(id: Note.Id, newNote: Note): Mono<Note> {
        return repository.save(toEntity(newNote))
                .map { entity -> fromEntity(entity) }
    }

    private fun fromEntity(entity: NoteEntity): Note {
        val created = ZonedDateTime.of(entity.created, ZoneId.of(entity.timezone))
        return Note(Note.Id(entity.id), entity.title, entity.markdown, entity.html, created)
    }

    private fun toEntity(note: Note): NoteEntity {
        val created = note.created.toLocalDateTime()
        return NoteEntity(note.id.id, note.title, note.markdown, note.html, created, note.created.zone.id)
    }
}
