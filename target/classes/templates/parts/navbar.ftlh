<#import "login.ftlh" as l>
<#include "security.ftlh">
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="/">Адаптация</a>
    <button
            class="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation"
    >
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/tasks/"
                >Задачи <span class="sr-only">(current)</span></a
                >
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/calendar/">Календарь</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/notes/">Заметки</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/faq/">Библия атома</a>
            </li>
        </ul>
        <#if known><@l.logout/></#if>
    </div>
</nav>