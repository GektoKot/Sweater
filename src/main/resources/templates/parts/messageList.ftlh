
<#include "security.ftlh">
<#import "pager.ftlh" as p>

<@p.pager url page/>

<div class="card-columns">
    <#list page.getContent() as message>
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
            <div style="background-color: lightblue" class="card-footer text-muted container">
                <div class="row">
                    <a class="col align-self-center" href="/user-messages/${message.author.id}">${message.getAuthorName()}</a>
                    <a class="col align-self-center" href="#">
<#--                        <#if message.isLiked>-->
                        <#if true>
                            <i class="fas fa-award"></i>
                        <#else >
                            <i class="fal fa-award"></i>
                        </#if>
<#--                        ${message.likes}-->

                    </a>


                    <#if message.author.id == currentUserId>
                        <a class="col btn btn-info" href="/user-messages/${message.author.id}?message=${message.id}">
                            Peredelat'
                        </a>
                    </#if>
                </div>
            </div>
        </div>
    <#else>
        vse molchat
    </#list>
</div>
<@p.pager url page/>