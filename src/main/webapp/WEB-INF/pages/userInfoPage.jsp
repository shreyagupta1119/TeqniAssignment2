<%--
  Created by IntelliJ IDEA.
  User: shreya
  Date: 12/1/17
  Time: 12:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page session="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<jsp:include page="_menu.jsp" />

<h1> Your birthdate is: ${message}</h1>
</body>
</html>
