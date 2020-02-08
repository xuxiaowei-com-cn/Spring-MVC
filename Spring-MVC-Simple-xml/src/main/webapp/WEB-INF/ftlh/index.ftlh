<#assign ctx=springMacroRequestContext.contextPath/>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>Spring-MVC-Simple</title>

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">

    <link rel="stylesheet" type="text/css" media="all" href="${ctx}/static/css/style.css"/>

</head>
<body>

<h2>Spring-MVC-Simple</h2>

<h3>FreeMarkerViewResolver 视图解析器</h3>

<hr>

<h3>从 Session 中获取</h3>

<@sec.authorize access="isAuthenticated()">
    <h4>${'用户名：' + Session.SPRING_SECURITY_CONTEXT.authentication.name}</h4>
</@sec.authorize>
<@sec.authorize access="isAuthenticated()">
    <#if Session.SPRING_SECURITY_CONTEXT.authentication.credentials ??>
        <h4>${'用户证书：' + Session.SPRING_SECURITY_CONTEXT.authentication.credentials}</h4>
    <#else>
        <h4>用户证书：null</h4>
    </#if>
</@sec.authorize>
<@sec.authorize access="isAuthenticated()">
    <h4>${'用户详细信息：' + Session.SPRING_SECURITY_CONTEXT.authentication.principal}</h4>
</@sec.authorize>
<@sec.authorize access="isAuthenticated()">
    <h4>${'用户细节：' + Session.SPRING_SECURITY_CONTEXT.authentication.details}</h4>
</@sec.authorize>
<@sec.authorize access="isAuthenticated()">
    <h4>用户权限：
        <#list Session.SPRING_SECURITY_CONTEXT.authentication.authorities as authoritie>
            <#if authoritie_index == 0>[</#if>${authoritie}<#if authoritie_has_next>, </#if><#if !authoritie_has_next>]</#if>
        </#list>
    </h4>
</@sec.authorize>

<h4>${modelValue}</h4>

<hr>

<a href="${ctx}/html/index">Thymeleaf 页面</a><br>

<a href="${ctx}/ftlh/index">FreeMarker 页面</a><br>

<a href="${ctx}/jsp/index">JSP 页面</a><br>

<a href="${ctx}/ui/index.xhtml">JSF 页面</a><br>

<br>

<@sec.authorize access="!isAuthenticated()">
    <button id="login-page"><a href="${ctx}/login">登录页面</a></button>
</@sec.authorize>

<@sec.authorize access="isAuthenticated()">
    <button id="logout-button">注销登录</button>
</@sec.authorize>

</body>

<script type="text/javascript" src="${ctx}/static/js/jQuery/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/logout.js"></script>

<script type="text/javascript">

    $(function () {
        console.log("文档就绪函数", new Date().toLocaleString())
    });

    $("#logout-button").click(function () {
        logout("${ctx}/logout.json")
    });

</script>

</html>