<#-- @ftlvariable name="note" type="de.mkammerer.noke.business.Note" -->

<#include "includes/header.ftlh">

<h1>${note.title}</h1>

<div>
${note.html?no_esc}
</div>

<form action="<@spring.url "/note/${note.id}/delete"/>" method="post">
    <button type="submit" class="btn btn-danger">Delete note</button>
</form>

<#include "includes/footer.ftlh">
