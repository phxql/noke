package de.mkammerer.noke.storage

import de.mkammerer.noke.business.Note
import de.mkammerer.noke.business.NoteRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.concurrent.CopyOnWriteArrayList

// @Repository
class InMemoryNoteRepository : NoteRepository {
    private val notes: MutableList<Note> = CopyOnWriteArrayList<Note>()

    override fun listAll(): Flux<Note> = Flux.fromIterable(notes)

    override fun findById(id: Note.Id): Mono<Note> {
        return Flux.fromIterable(notes)
                .filter { it.id == id }
                .singleOrEmpty()
    }

    override fun add(note: Note): Mono<Note> {
        return Mono.create { sink ->
            notes.add(note)
            sink.success(note)
        }
    }

    override fun delete(id: Note.Id): Mono<Boolean> {
        return Mono.create { sink ->
            val removed = notes.removeIf { it.id == id }
            sink.success(removed)
        }
    }
}