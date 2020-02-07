<#assign ctx=springMacroRequestContext.contextPath/>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>Spring-MVC-Simple</title>

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"/>

    <link rel="stylesheet" type="text/css" media="all" href="${ctx}/static/css/style.css"/>

</head>
<body>

<h2>Spring-MVC-Simple</h2>

<h3>FreeMarkerViewResolver 视图解析器</h3>

</body>

<script type="text/javascript" src="${ctx}/static/js/jQuery/jquery-3.4.1.min.js"></script>

</html>