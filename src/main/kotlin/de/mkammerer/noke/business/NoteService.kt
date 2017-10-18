package de.mkammerer.noke.business

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface NoteService {
    fun listAll(): Flux<Note>

    fun findById(id: Note.Id): Mono<Note>

    fun add(title: String, markdown: String): Mono<Note>

    fun delete(id: Note.Id): Mono<Boolean>
}

interface NoteRepository {
    fun listAll(): Flux<Note>

    fun findById(id: Note.Id): Mono<Note>

    fun add(note: Note): Mono<Note>

    fun delete(id: Note.Id): Mono<Boolean>
}

@Service
class NoteServiceImpl(
        private val noteRepository: NoteRepository,
        private val uuidFactory: UUIDFactory,
        private val markdownRenderer: MarkdownRenderer
) : NoteService {
    override fun listAll(): Flux<Note> = noteRepository.listAll()

    override fun findById(id: Note.Id): Mono<Note> = noteRepository.findById(id)

    override fun add(title: String, markdown: String): Mono<Note> {
        return uuidFactory.create()
                .map { id -> Note(Note.Id(id), title, markdown, markdownRenderer.render(markdown)) }
                .flatMap { note -> noteRepository.add(note) }
    }

    override fun delete(id: Note.Id): Mono<Boolean> = noteRepository.delete(id)
}