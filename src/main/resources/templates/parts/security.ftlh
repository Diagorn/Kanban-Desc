<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    shortName = user.getUsernameWithInitials()
    avatar = user.getFileName()
    >
<#else>
    <#assign
    name = ""
    shortName = ""
    avatar="defaultavatar.png"
    >
</#if>