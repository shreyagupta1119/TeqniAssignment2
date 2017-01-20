<%--
  Created by IntelliJ IDEA.
  User: shreya
  Date: 12/1/17
  Time: 1:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head><title>Registration</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <style>
        h3{
            padding-bottom:30px;
        }
        form { width: 1800px; }
        label {
            display: inline-block;
            width:500px;
            text-align: right;
            padding-right:20px;
            margin-bottom: 13px;
        }
        button{
            height:30px;
            width:150px;
            margin: -20px -50px;
            position:relative;
            top:50%;
            left:32%;
            margin-top: 90px;
        }

        select{
            width: 254px;
        }
        .g-recaptcha {
            height:30px;
            width:150px;
            margin: -20px -50px;
            position:relative;
            top:50%;
            left:30%;
            margin-top: 10px;
        }


    </style>

</head>
<body>
<jsp:include page="_menu.jsp" />


<h1 align="center">Registration form</h1>

<!-- /login?error=true -->
<c:if test="${param.error == 'true'}">
    <div style="color:red;margin:10px 0px;">

        Registration Failed!!!<br />
        Reason :  ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}

    </div>
</c:if>

<h3 align="center">Enter your details:</h3>


    <form name="oform" id="myform" class="form-horizontal" action="javascript://;" onsubmit="myfunction()" method='POST'>



            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="username">Username*</label>

                    <input id="username" minlength="4"  maxlength="15" name="username" type="text" placeholder="Enter Username" class="form-control input-md" pattern="[A-Za-z-0-9]+" title="Username cannot include spaces and special characters" size="30" required="">


            </div>

            <div class="control-group">
                <!-- E-mail -->
                <label class="control-label" for="email">E-mail*</label>

                    <input type="email" id="email" name="email" placeholder="abc@xyz.com" size="30" class="form-control input-md"  title="Please enter valid Email address" required/>
            </div>

        </div>

        <div class="control-group">
            <!-- Date of birth -->
            <label class="control-label" for="dob">Date Of Birth*</label>

            <input style="width:254px;" type="DATE" id="dob" name="dob" placeholder="dd/mm/yyyy" size="30"  pattern="^[0-9]{1,2}\-[0-9]{1,2}\-[0-9]{1,4}$" class="form-control input-md" required="">
        </div>

            <div class="control-group">
                <!-- Password-->
                <label class="control-label" for="password">Password*</label>

                    <input type="password" id="password" minlength="4" name="password" size="30" placeholder="Password should be at least 6 characters"
                           title="Password must contain at least one numeric digit, one uppercase and one lowercase letter"
                           pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$" class="input-xlarge" onchange="checkPass()" required>

            </div>

            <div class="control-group">
                <!-- Password -->
                <label class="control-label"  for="password_confirm">Password (Confirm)*</label>

                    <input type="password" id="password_confirm" name="password_confirm" size="30" placeholder="Reenter password" class="input-xlarge" onchange="checkPass()" required>
                <span id='message'></span>

            </div>

        <div class="control-group">
            <label class="control-label" for="type" id="type">Select Type*</label>
            <select id="type1">
                <option>user</option>
                <option>admin</option>

            </select>
        </div>

        <div class="g-recaptcha" id="rcaptcha" data-sitekey="6Lf2QBIUAAAAAD_muAfFieCtyhlJCDCZUIPxT9t-">

        </div>
               <strong align="center" style="left: 100%; margin-top: 600px; margin-left: 800px;"> captcha is compulsory</strong>
        <div class="control-group">
            <!-- Button -->
            <div class="controls">
                <button id="submit" onclick="validateForm()" disabled>Submit </button>
            </div>
        </div>

</form>

<p><br><br>Note: Please make sure your details are correct before submitting form and that all fields marked with * are completed!.</p>

<script type="text/javascript">
    function checkPass() {
        $('#password, #password_confirm').on('keyup', function () {
            if ($('#password').val() == $('#password_confirm').val()) {
                $('#message').html('Matching').css('color', 'green');
                document.getElementById('submit').disabled=false;
            } else
                $('#message').html('Not Matching').css('color', 'red');

        });
    }
    function validateForm() {

    var $recaptcha = document.querySelector('#g-recaptcha-response');

        if($recaptcha) {
             $('#message1').html('this field is compulsory').css('color', 'green');
            $recaptcha.setAttribute("required", "required");
        }
    };


        function myfunction() {

        var data = {};
        data["username"]=$('#username').val();
        data["email"]=$('#email').val();
        data["dob"]=$('#dob').val();
        data["password"]=$('#password').val();
        data["type"]=$('#type1').val();
        console.log(data);
        $.ajax({
            url: '/teqni/userinfo', // url where to submit the request
            type : 'POST', // type of action POST || GET
            contentType : 'application/json', // data type
            async: true,
            data : JSON.stringify(data), // post data || get data
            success : function(data) {
                // you can see the result from the console
                // tab of the developer tools
                $('#target').html(data.message);
                console.log(data);
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