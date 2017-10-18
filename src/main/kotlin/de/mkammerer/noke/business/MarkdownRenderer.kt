package de.mkammerer.noke.business

import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import org.springframework.stereotype.Service

interface MarkdownRenderer {
    fun render(markdown: String): String
}

@Service
class MarkdownRendererImpl : MarkdownRenderer {
    private val parser = Parser.builder().build()
    private val renderer = HtmlRenderer.builder().build();

    override fun render(markdown: String): String {
        val document = parser.parse(markdown)
        return renderer.render(document)
    }
}