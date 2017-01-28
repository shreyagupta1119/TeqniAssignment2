<%--
  Created by IntelliJ IDEA.
  User: shreya
  Date: 12/1/17
  Time: 12:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head><title>Login</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script></head>
<body>
<jsp:include page="_menu.jsp" />


<h1>Login</h1>

<!-- /login?error=true -->
<c:if test="${param.error == 'true'}">
    <div style="color:red;margin:10px 0px;">

        Login Failed!!!<br />
        Reason :  ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}

    </div>
</c:if>

<h3>Enter user name and password:</h3>

<form name='f' action="javascript://;" onsubmit="myfunction()" method='POST'>
    <table>
        <tr>
            <td>Username:</td>
            <td><input id="username" type='text' name='username'  required></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input id="password" type='password' name='password' required></td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="submit" onsubmit="myfunction()"></td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    function myfunction() {
        var data = {};
        data["username"]=$('#username').val();
        data["password"]=$('#password').val();
        console.log(data);
        $.ajax({
            url: '/teqni/logincred', // url where to submit the request
            type : 'POST', // type of action POST || GET
            contentType : 'application/json', // data type
            async: true,
            data : JSON.stringify(data), // post data || get data
            success : function(data, status, xhr) {
                console.log(xhr.getResponseHeader("Token"));
                $('#target').html(data.message);
                document.open();
                document.write(data);
                document.close();
            },
            error: function(xhr, resp, text) {
                console.log(xhr, resp, text);
            }
        });

    }
</script>

</body>
</html>
