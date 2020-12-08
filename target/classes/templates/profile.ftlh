<#import "parts/common.ftlh" as c>


<@c.page>
    <h6>${username}</h6>
    <form method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password: </label>
            <div class="col-sm-5">
                <input type="password" name="password" class="form-control" placeholder="password"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Email: </label>
            <div class="col-sm-5">
                <input type="email" name="email" class="form-control" placeholder="email@mail.mail" value="${email!}"/>
            </div>
        </div>


        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button class="btn btn-primary mr-2" type="submit">Save</button>
    </form>
</@c.page>

