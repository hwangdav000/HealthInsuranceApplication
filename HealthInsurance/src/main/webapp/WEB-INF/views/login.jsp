<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page isELIgnored="false" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib prefix="sec"
uri="http://www.springframework.org/security/tags" %> <%@ taglib
uri="http://www.springframework.org/tags/form" prefix="frm" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Insurance - Login</title>
    <link rel="shortcut icon" href="images/favicon.png" />
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css" rel="stylesheet" type="text/css">
    <link href="css/icons.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <style>
        /* Custom styles to ensure the form is not covered by the header */
        .form-container {
            margin-top: 140px; /* Adjust this value based on the height of your header */
        }
        body {
	  		overflow: hidden; /* Hide scrollbars */
		}
    </style>
</head>

<body data-target=".navbar-fixed-top">
  <header>
        <div class="top-bar">
            <div class="container" style="max-width:100%;">
                <div class="row">
                    <div class="col-sm-6 address">
                        <i class="ti-location-pin"></i> 224 Range Plaza, Suite 200, New York, NY 10001
United States
                    </div>
                    <div class="col-sm-6 social">
                        <ul>
                            <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                            <li><a href="#"><i class="fa fa-facebook-square"></i></a></li>
                            <li><a href="#"><i class="fa fa-linkedin-square"></i></a></li>
                            <li><a href="#"><i class="fa fa-pinterest"></i></a></li>
                            <li><a href="#"><i class="fa fa-instagram"></i></a></li>
                            <li><a href="#"><i class="fa fa-youtube"></i></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
            <div class="navbar-header">
			    <div style="display: flex; align-items: center; margin-left:20px">
			        <img src="images/caeduceus.svg" alt="Caeduceus Icon" width="30" height="30" style="margin-bottom: 9px;">
			        <a class="navbar-brand" href="/home" style="margin-left: 10px;">
			            UnitedHealth <span>Assurance</span>
			        </a>
			    </div>
			    <div>
			        <p style="margin: 5px 0; margin-left:27px">Call Us Now <b>+1 466-224-4428</b></p>
			    </div>
				</div>
            </div>
        </nav>
    </header>

	<div class="container" style="margin-top: 300px;">
			
	    <div class="d-flex justify-content-center form-container">
	        
	        <frm:form action="login" method="post">
	        	<div class="form-group">
	                <h2 style="color:#007bff;">Login to UnitedHealth Assurance</h2>
	            </div>
	            <div class="form-group">
	                <label for="username">Username:</label>
	                <input id="username" class="form-control" type="text" name="username" required>
	            </div>
	
	            <div class="form-group">
	                <label for="password">Password:</label>
	                <input id="password" class="form-control" type="password" name="password" required>
	            </div>
	            
	            <div class="text-muted">
	                Don't have an account? <a href="/register">Create one here!</a>
	            </div>
	
	            <input class="btn btn-primary mt-3" type="submit" value="Submit">
	            <sec:csrfInput />
	        </frm:form>
	    </div>
	</div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-hover-dropdown/2.2.1/bootstrap-hover-dropdown.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
</body>

</html>
