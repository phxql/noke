<#include "includes/header.ftl">

<h1>Add new note</h1>

<form method="post">
    <label for="title">Title:</label>
<@spring.formInput "form.title" "required"/>

    <label for="content">Content:</label>
<@spring.formInput "form.content" "required"/>

    <input type="submit" value="Add note">
</form>

<#include "includes/footer.ftl">