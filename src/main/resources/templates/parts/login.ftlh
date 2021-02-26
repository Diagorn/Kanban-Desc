<#include "security.ftlh">

<#macro user_inputs>
    <div class="form-group">
        <label for="exampleInputEmail1">Адрес электронной почты</label>
        <input name="username" type="email" class="form-control"
               id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Введите электронную почту">
    </div>

    <div class="form-group">
        <label for="exampleInputPassword1">Пароль</label>
        <input name="password" type="password" class="form-control"
               id="exampleInputPassword1" placeholder="Введите пароль">
    </div>
</#macro>

<#macro login path>
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <form action="/login" method="post">
                <@user_inputs />
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-primary">Войти</button>
            </form>
        </div>
    </div>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button class="btn btn-light ml-3" type="submit">Выйти</button>
    </form>
</#macro>