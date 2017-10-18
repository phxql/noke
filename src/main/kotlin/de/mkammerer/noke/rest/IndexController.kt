package de.mkammerer.noke.rest

import de.mkammerer.noke.business.NoteService
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import reactor.core.publisher.Mono

@Controller
@RequestMapping("/", produces = arrayOf(MediaType.TEXT_HTML_VALUE))
class IndexController(
        private val noteService: NoteService
) {
    @GetMapping
    fun index(): Mono<ModelAndView> {
        return noteService.listAll()
                .collectList()
                .map { notes -> ModelAndView("index", mapOf("notes" to notes)) }
    }
}