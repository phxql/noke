package de.mkammerer.noke.rest

import de.mkammerer.noke.business.Note
import de.mkammerer.noke.business.NoteService
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import reactor.core.publisher.Mono

@Controller
@RequestMapping("/note", produces = arrayOf(MediaType.TEXT_HTML_VALUE))
class NoteController(
        private val noteService: NoteService
) {
    @GetMapping("{noteId}")
    fun noteDetails(@PathVariable noteId: String): Mono<ModelAndView> {
        return noteService.findById(Note.Id.parse(noteId))
                .map { note -> ModelAndView("note", mapOf("note" to note)) }
    }

    @GetMapping("add")
    fun addNote(): ModelAndView = ModelAndView("add-note", mapOf("form" to AddNoteForm("", "")))

    @PostMapping("add", consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    fun addNoteSubmit(@ModelAttribute form: NoteController.AddNoteForm): Mono<String> {
        return noteService.add(form.title, form.content)
                .map { "redirect:/" }
    }

    class AddNoteForm(
            var title: String,
            var content: String
    )
}