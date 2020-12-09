<#include "security.ftlh">
<#import "pager.ftlh" as p>

<@p.pager url page/>

<div class="card-columns">
    <#list page.getContent() as page>
        <div class="card my-3">
            <div>
                <#if page.filename??>
                    <img src="/img/${page.filename}" class="card-img-top">
                </#if>
            </div>
            <div class="m-2">
                <span>${page.getText()}</span><br/>
                <i>#${page.getTag()}</i>
            </div>
            <div style="background-color: lightblue" class="card-footer text-muted">
                <a href="/user-messages/${page.author.id}">${page.getAuthorName()}</a>
                <#if page.author.id == currentUserId>
                    <a class="btn btn-dark" href="/user-messages/${page.author.id}?message=${page.id}">
                        Переделать сообщение
                    </a>
                </#if>
            </div>
        </div>
    <#else>
        vse molchat
    </#list>
</div>
<@p.pager url page/>