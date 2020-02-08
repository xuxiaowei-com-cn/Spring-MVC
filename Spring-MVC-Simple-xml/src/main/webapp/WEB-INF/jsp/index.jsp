<%--
  Created by IntelliJ IDEA.
  User: xuxiaowei
  Date: 2020-02-03
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

<hr>

<h3>从 Session 中获取</h3>

<sec:authorize access="isAuthenticated()">
    <h4>用户名：${sessionScope.SPRING_SECURITY_CONTEXT.authentication.name}</h4>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <h4>用户证书：${sessionScope.SPRING_SECURITY_CONTEXT.authentication.credentials}</h4>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <h4>用户详细信息：${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}</h4>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <h4>用户细节：${sessionScope.SPRING_SECURITY_CONTEXT.authentication.details}</h4>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <h4>用户权限：
        <c:forEach items="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.authorities}" var="authoritie"
                   varStatus="authoritieStatus">
            <c:if test="${authoritieStatus.first}">[</c:if><span>${authoritie.authority}</span><c:if test="${!authoritieStatus.last}">, </c:if><c:if test="${authoritieStatus.last}">]</c:if>
        </c:forEach>
    </h4>
</sec:authorize>

<h4>${modelValue}</h4>

<hr>

<a href="${pageContext.request.contextPath}/html/index">Thymeleaf 页面</a><br>

<a href="${pageContext.request.contextPath}/ftlh/index">FreeMarker 页面</a><br>

<a href="${pageContext.request.contextPath}/jsp/index">JSP 页面</a><br>

<a href="${pageContext.request.contextPath}/ui/index.xhtml">JSF 页面</a><br>

<br>

<sec:authorize access="!isAuthenticated()">
    <button id="login-page"><a href="${pageContext.request.contextPath}/login">登录页面</a></button>
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
