<%--
  Created by IntelliJ IDEA.
  User: shreya
  Date: 28/1/17
  Time: 9:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body>
<form id="oform" name="myform" action="javascript://;">
<div style="border: 1px solid #ccc;padding:5px;margin-bottom:20px;">

    <a href="${pageContext.request.contextPath}/welcome">Home</a>

   | &nbsp;

<!--    <a href="${pageContext.request.contextPath}/userInfo" onclick="tokenSetter()">User Info</a>

    | &nbsp;

    --><a href="${pageContext.request.contextPath}/logout" >Logout</a>


    </div>

</form>
<h1> Your birthdate is : ${message}</h1>

<script type="text/javascript">
    function tokenSetter(){
        var req = new XMLHttpRequest();
        req.open('GET', document.location, false);
        req.send(null);
        var headers = req.getAllResponseHeaders().toLowerCase();
        alert(headers);
        $.ajax({
            url: '/teqni/userInfo', // url where to submit the request
            type : 'POST', // type of action POST || GET
            contentType : 'application/json', // data type
            async: true,
            data : JSON.stringify(data), // post data || get data
            success : function(data) {
                // you can see the result from the console
                // tab of the developer tools
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
