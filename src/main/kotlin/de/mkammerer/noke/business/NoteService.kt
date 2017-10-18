package de.mkammerer.noke.business

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface NoteService {
    fun listAll(): Flux<Note>

    fun findById(id: Note.Id): Mono<Note>

    fun add(title: String, content: String): Mono<Note>
}

interface NoteRepository {
    fun listAll(): Flux<Note>

    fun findById(id: Note.Id): Mono<Note>

    fun add(note: Note): Mono<Note>
}

@Service
class NoteServiceImpl(
        private val noteRepository: NoteRepository,
        private val uuidFactory: UUIDFactory
) : NoteService {
    override fun listAll(): Flux<Note> = noteRepository.listAll()

    override fun findById(id: Note.Id): Mono<Note> = noteRepository.findById(id)

    override fun add(title: String, content: String): Mono<Note> {
        return uuidFactory.create()
                .map { id -> Note(Note.Id(id), title, content) }
                .flatMap { note -> noteRepository.add(note) }
    }
}