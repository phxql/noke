<#-- @ftlvariable name="notes" type="java.util.List<de.mkammerer.noke.business.Note>" -->

<#include "includes/header.ftl">

<h1>Index</h1>

<ul>
<#list notes as note>
    <li>
        <a href="/note/${note.id}">${note.title}</a>
    </li>
</#list>
</ul>


<a href="<@spring.url "note/add"/>">Add note</a>

<#include "includes/footer.ftl">