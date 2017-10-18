package de.mkammerer.noke.storage.cassandra

import de.mkammerer.noke.business.Note
import de.mkammerer.noke.business.NoteRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class CassandraNoteRepository(
        private val repository: ReactiveCassandraNoteRepository
) : NoteRepository {
    override fun listAll(): Flux<Note> {
        return repository.findAll()
                .map { fromEntity(it) }
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

    private fun fromEntity(entity: NoteEntity): Note = Note(Note.Id(entity.id), entity.title, entity.markdown, entity.html)

    private fun toEntity(note: Note): NoteEntity = NoteEntity(note.id.id, note.title, note.markdown, note.html)
}
