<%--
  Created by IntelliJ IDEA.
  User: shreya
  Date: 12/1/17
  Time: 12:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="border: 1px solid #ccc;padding:5px;margin-bottom:20px;">

    <a href="${pageContext.request.contextPath}/welcome">Home</a>

    <c:if test="${pageContext.request.userPrincipal.name != null}">

        | &nbsp;
        <a href="${pageContext.request.contextPath}/logout">Logout</a>

    </c:if>

</div>
