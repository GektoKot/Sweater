<#import "parts/common.ftlh" as c>

<@c.page>
    User Editor:
    <form action="/user" method="post">

        <input type="text" name="username" value="${user.getUsername()}">

        <#list roles as role>
            <div>
                <lable><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</lable>
            </div>
        </#list>
        <input type="hidden" name="userId" value="${user.id}" >
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit"> save</button>

    </form>



</@c.page>