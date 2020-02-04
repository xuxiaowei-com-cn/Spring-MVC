<#assign ctx=springMacroRequestContext.contextPath/>
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

<h4>${name}</h4>
<h4>${credentials}</h4>
<h4>${principal}</h4>
<h4>${details}</h4>
<h4>${authorities}</h4>

<a href="${ctx}/html/index">Thymeleaf 页面</a><br>

<a href="${ctx}/ftlh/index">FreeMarker 页面</a><br>

<a href="${ctx}/jsp/index">JSP 页面</a><br>

<a href="${ctx}/ui/index.xhtml">JSF 页面</a><br>

<br>

<button id="logout-button">注销登录</button>

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