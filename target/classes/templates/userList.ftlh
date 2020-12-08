<#import "parts/common.ftlh" as c>

<@c.page>
    List of users:
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Role</th>
            <th>Email</th>
            <th>move</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.getRoles() as role>${role}<#sep>, </#list></td>
                <td>${user.email!}</td>
                <td>
                    <div class="form-group mt-3">
                        <div class="form-group">
                            <form action="/user/${user.id}" method="get">
                                <input type="hidden" name="userId" value="${user.id}">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <button type="submit"> edit</button>
                            </form>
                        </div>
                        <div class="form-group">
                            <form action="/user/delete" method="post">
                                <input type="hidden" name="userId" value="${user.id}">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <button type="submit"> delete</button>
                            </form>
                        </div>


                    </div>

                </td>
            </tr>




        </#list>
        </tbody>

    </table>

</@c.page>