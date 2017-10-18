<#include "includes/header.ftlh">

<h1>Add new note</h1>

<form method="post">
    <div class="form-group">
        <label for="title">Title:</label>
    <@spring.formInput "form.title" 'class="form-control" required'/>
    </div>

    <div class="form-group">
        <label for="content">Content:</label>
    <@spring.formTextarea "form.content" 'class="form-control" required rows="3"'/>
    </div>

    <button type="submit" class="btn btn-success">Add note</button>
</form>

<#include "includes/footer.ftlh">