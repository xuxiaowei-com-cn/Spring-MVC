<%--
  Created by IntelliJ IDEA.
  User: xuxiaowei
  Date: 2020-02-03
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <title>Spring-MVC-Simple</title>

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">

    <!-- 引入 CSS -->
    <link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/static/css/style.css"/>

</head>
<body>

<h2>Spring-MVC-Simple</h2>

<h3>InternalResourceViewResolver 视图解析器</h3>

<h4>${name}</h4>
<h4>${credentials}</h4>
<h4>${principal}</h4>
<h4>${details}</h4>
<h4>${authorities}</h4>

<h4>${modelValue}</h4>

<a href="${pageContext.request.contextPath}/html/index">Thymeleaf 页面</a><br>

<a href="${pageContext.request.contextPath}/ftlh/index">FreeMarker 页面</a><br>

<a href="${pageContext.request.contextPath}/jsp/index">JSP 页面</a><br>

<a href="${pageContext.request.contextPath}/ui/index.xhtml">JSF 页面</a><br>

<br>

<sec:authorize access="!isAuthenticated()">
<button id="login-page">登录页面</button>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
<button id="logout-button">注销登录</button>
</sec:authorize>

</body>

<!-- 引入 JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jQuery/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/logout.js"></script>

<script type="text/javascript">

    $(function () {
        console.log("文档就绪函数", new Date().toLocaleString())
    });

    $("#logout-button").click(function () {
        logout("${pageContext.request.contextPath}/logout.json")
    });

</script>

</html>
