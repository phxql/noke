<#-- @ftlvariable name="note" type="de.mkammerer.noke.business.Note" -->

<#include "includes/header.ftl">

<h1>${note.title}</h1>

<pre>
${note.content}
</pre>

<form action="<@spring.url "/note/${note.id}/delete"/>" method="post">
    <input type="submit" value="Delete note"/>
</form>

<#include "includes/footer.ftl">
