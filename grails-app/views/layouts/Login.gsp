<%--
  Created by IntelliJ IDEA.
  User: mpaulez
  Date: 3/21/16
  Time: 21:24
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Student Course Allocation - Login</title>

    <asset:link rel="shortcut icon" href="favicon.ico" type="image/x-icon"/>

    <!-- Bootstrap Core CSS -->
    <link href="${request.contextPath}/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${request.contextPath}/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${request.contextPath}/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${request.contextPath}/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- CareConnect Specific Styles -->
    <link href="${request.contextPath}/css/sca.css" rel="stylesheet" type="text/css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div class="container" data-background-src="../images/bg2.jpg">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Student Course Allocation:<br>Please Sign In</h3>
                </div>
                <div class="panel-body">

                    <div class="fieldcontain">
                        <g:if test="${error == true}" >
                            <label style="color: red; margin-left: 50px">${errorLabel}</label>
                        </g:if>
                    </div>

                    <div class="">
                        <g:if test="${register == true}" >
                            <label style="color: #f9b800; margin-left: 50px">Successfully Registered. Please log in</label>
                        </g:if>
                    </div>

                    <g:form action="login" controller="Login" >
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="User Name" name="userName"  autofocus>
                                %{--<input class="form-control" placeholder="E-mail" name="email" type="email" autofocus>--}%
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Password" name="password" type="password" value="">
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                </label>
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            %{--<a href="CareConnect" class="btn btn-lg btn-success btn-block">Login</a>--}%

                            <g:actionSubmit class="btn btn-lg btn-success btn-block"  value="Login" />
                            <g:actionSubmit class="btn btn-lg btn-success btn-block"  value="Register" action="Register"/>


                        </fieldset>
                    </g:form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="${request.contextPath}/bower_components/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${request.contextPath}/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="${request.contextPath}/bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="${request.contextPath}/dist/js/sb-admin-2.js"></script>

</body>

</html>