<#-- @ftlvariable name="add" type="java.lang.Boolean" -->
<#-- @ftlvariable name="form" type="de.mkammerer.noke.rest.NoteController.NoteForm" -->
<#include "includes/header.ftlh">

<#if add>
<h1>Add new note</h1>
<#else>
<h1>Edit note ${form.title}</h1>
</#if>

<form method="post">
    <div class="form-group">
        <label for="title">Title:</label>
    <@spring.formInput "form.title" 'class="form-control" required'/>
    </div>

    <div class="form-group">
        <label for="content">Content:</label>
    <@spring.formTextarea "form.content" 'class="form-control" required rows="3"'/>
    </div>

<#if add>
    <button type="submit" class="btn btn-success">Add note</button>
<#else>
    <button type="submit" class="btn btn-success">Save changes</button>
</#if>
    <a href="<@spring.url "/"/>" class="btn btn-secondary">Back to list</a>

</form>

<#include "includes/footer.ftlh">