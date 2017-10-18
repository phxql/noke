package de.mkammerer.noke.storage

import de.mkammerer.noke.business.Note
import de.mkammerer.noke.business.NoteRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList

@Repository
class InMemoryNoteRepository : NoteRepository {
    private val notes: MutableList<Note> = CopyOnWriteArrayList<Note>()

    init {
        for (i in 1..5) {
            notes.add(Note(Note.Id(UUID.randomUUID()), "Note #$i", "Note content #$i"))
        }
    }

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
}