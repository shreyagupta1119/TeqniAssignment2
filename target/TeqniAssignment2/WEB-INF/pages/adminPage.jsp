<%--
  Created by IntelliJ IDEA.
  User: shreya
  Date: 12/1/17
  Time: 12:05 AM
  To change this template use File | Settings | File Templates.
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>{title}</title>
</head>
<body>
<jsp:include page="_menu.jsp" />

<h2>Admin Page</h2>


<h3>Welcome : ${pageContext.request.userPrincipal.name}</h3>

<b>This is protected page!</b>
</body>
</html>

