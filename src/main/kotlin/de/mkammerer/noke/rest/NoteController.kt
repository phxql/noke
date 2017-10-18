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
    fun addNote(): ModelAndView {
        return ModelAndView("add-edit-note", mapOf(
                "form" to NoteForm("", ""),
                "add" to true
        ))
    }

    @PostMapping("add", consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    fun addNoteSubmit(@ModelAttribute form: NoteController.NoteForm): Mono<String> {
        return noteService.add(form.title, form.content)
                .map { note -> "redirect:/note/${note.id}" }
    }

    @GetMapping("{noteId}/edit")
    fun editNote(@PathVariable noteId: String): Mono<ModelAndView> {
        return noteService.findById(Note.Id.parse(noteId))
                .map { note ->
                    ModelAndView("add-edit-note", mapOf(
                            "form" to NoteForm.fromNote(note),
                            "add" to false
                    ))
                }
    }

    @PostMapping("{noteId}/edit", consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    fun editNoteSubmit(@PathVariable noteId: String, @ModelAttribute form: NoteController.NoteForm): Mono<String> {
        return noteService.edit(Note.Id.parse(noteId), form.title, form.content)
                .map { note -> "redirect:/note/${note.id}" }
    }


    @PostMapping("{noteId}/delete", consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    fun deleteNote(@PathVariable noteId: String): Mono<String> {
        return noteService.delete(Note.Id.parse(noteId))
                .map { "redirect:/" }
    }

    class NoteForm(
            var title: String,
            var content: String
    ) {
        companion object {
            fun fromNote(note: Note): NoteForm = NoteForm(note.title, note.markdown)
        }
    }
}