<#include "security.ftlh">

<div class="card-columns">
    <#list messages as message>

        <div class="card my-3">
            <div>
                <#if message.filename??>
                    <img src="/img/${message.filename}" class="card-img-top">
                </#if>
            </div>
            <div class="m-2">
                <span>${message.getText()}</span><br/>
                <i>#${message.getTag()}</i>
            </div>
            <div style="background-color: lightblue" class="card-footer text-muted">
                <a href="/user-messages/${message.author.id}">${message.getAuthorName()}</a>
                <#if message.author.id == currentUserId>
                    <a class="btn btn-dark" href="/user-messages/${message.author.id}?message=${message.id}">
                    Переделать сообщение
                    </a>
                </#if>

            </div>

        </div>

    <#else>
        vse molchat
    </#list>
</div>