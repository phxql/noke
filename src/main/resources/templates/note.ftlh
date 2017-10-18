<#-- @ftlvariable name="note" type="de.mkammerer.noke.business.Note" -->

<#include "includes/header.ftlh">

<h1>${note.title}</h1>

<div>
${note.html?no_esc}
</div>

<form action="<@spring.url "/note/${note.id}/delete"/>" method="post">
    <a href="<@spring.url "/note/${note.id}/edit"/>" class="btn btn-primary">Edit note</a>
    <button type="submit" class="btn btn-danger">Delete note</button>
    <a href="<@spring.url "/"/>" class="btn btn-secondary">Back to list</a>
</form>

<#include "includes/footer.ftlh">
