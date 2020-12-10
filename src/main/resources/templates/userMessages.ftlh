<#import "parts/common.ftlh" as c>


<@c.page>
    <h3>${userChannel.username}</h3>

    <#if !isCurrentUser>
        <#if isSubscriber>
            <a class="btn btn-danger" href="/user/unsubscribe/${userChannel.id}">
                ОтПодпизь.
            </a>
        <#else>
            <a class="btn btn-danger" href="/user/subscribe/${userChannel.id}">
                Подпизь.
            </a>
        </#if>

    </#if>
    <div class="container my-3">
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Subscriptions</div>
                        <div class="card-text">
                            <a href="/user/subscriptions/${userChannel.id}/list">${subscriptionsCount}</a>

                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Subscribers</div>
                        <div class="card-text">
                            <a href="/user/subscribers/${userChannel.id}/list">${subscribersCount}</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#if isCurrentUser && message??>
        <#include "parts/messageEdit.ftlh"/>
    </#if>

    <#include "parts/messageList.ftlh"/>

</@c.page>
