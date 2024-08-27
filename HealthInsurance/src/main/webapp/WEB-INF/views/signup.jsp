<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Signup to Health Insurance</title>
    <link rel="shortcut icon" href="images/favicon.png" />
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css" rel="stylesheet" type="text/css">
    <link href="css/icons.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    
    <style>
        body {
            height: 100vh;
        
        }
        .signup-container {
            margin-top: 5rem;
        }
        #gender-select {
            width: 100%;
            padding: 0.375rem 0.75rem;
            font-size: 1rem;
            line-height: 1.5;
            color: #495057;
            background-color: #fff;
            background-clip: padding-box;
            border: 1px solid #ced4da;
            border-radius: 0.25rem;
        }
        
    </style>
</head>
<body data-spy="scroll" data-target=".navbar-fixed-top">
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
								    
                <div>
                    <ul class=" navbar-nav d-flex" style="margin-right:30px; margin-left:25px;">
                         
					  <li class="nav-item dropdown">
			          <security:authorize access="isAuthenticated()">
			            <a
			              class="nav-link dropdown-toggle"
			              href="#"
			              id="navbarDropdownMenuLink"
			              data-toggle="dropdown"
			              aria-haspopup="true"
			              aria-expanded="false"
			            >
			              Welcome,
			              <span id="username" class="font-weight-bold font-italic ml-1"
			                ><security:authentication property="principal.username"
			              /></span>
			            </a>
			            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
			              <security:authorize access="hasAuthority('ADMIN')">
	          		         <a class="dropdown-item" href="/approvePolicy">Policy Issues</a>
	          			  </security:authorize>
	          			  <security:authorize access="hasAuthority('ADMIN')">
	          		         <a class="dropdown-item" href="/approveClaim">Approve Claims</a>
	          			  </security:authorize>
			              <security:authorize access="hasAuthority('USER')">
	          		         <a class="dropdown-item" href="/account">Account</a>
	          			  </security:authorize>
	          			  <security:authorize access="hasAuthority('USER')">
	          		         <a class="dropdown-item" href="/claim">Claims</a>
	          			  </security:authorize>
	          			  <security:authorize access="hasAuthority('USER')">
	          		         <a class="dropdown-item" href="/viewClaims">View Claims</a>
	          			  </security:authorize>

			              <a class="dropdown-item" href="/login?logout">Logout</a>
			            </div>
			          </security:authorize>
			          <security:authorize access="isAnonymous()">
			            <a href="/login" class="btn btn-light  btn-lg">Login</a>
			          </security:authorize>
			        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    
	   <div class="container signup-container" style="margin-top: 250px;">
	    <div class="row justify-content-center">
	        <div class="col-md-6">
	            <h2 class="text-center mb-4">Sign Up Form</h2>
	            <form action="signup" method="post">
	                <div class="form-group row">
	                    <label for="username" class="col-sm-4 col-form-label">Username:</label>
	                    <div class="col-sm-8">
	                        <input id="username" class="form-control" type="text" name="username" required>
	                    </div>
	                </div>
	                <div class="form-group row">
	                    <label for="password" class="col-sm-4 col-form-label">Password:</label>
	                    <div class="col-sm-8">
	                        <input id="password" class="form-control" type="password" name="password" required>
	                    </div>
	                </div>
	                <div class="form-group row">
	                    <label for="email" class="col-sm-4 col-form-label">Email:</label>
	                    <div class="col-sm-8">
	                        <input id="email" class="form-control" type="email" name="email" required>
	                    </div>
	                </div>
	                <div class="form-group row">
	                    <label for="firstname" class="col-sm-4 col-form-label">First Name:</label>
	                    <div class="col-sm-8">
	                        <input id="firstname" class="form-control" type="text" name="firstname" required>
	                    </div>
	                </div>
	                <div class="form-group row">
	                    <label for="lastname" class="col-sm-4 col-form-label">Last Name:</label>
	                    <div class="col-sm-8">
	                        <input id="lastname" class="form-control" type="text" name="lastname" required>
	                    </div>
	                </div>
	                <div class="form-group row">
	                    <label for="mobile" class="col-sm-4 col-form-label">Primary Phone Number:</label>
	                    <div class="col-sm-8">
	                        <input id="mobile" class="form-control" type="number" name="mobile" required>
	                    </div>
	                </div>
	                <div class="form-group row">
	                    <label for="address" class="col-sm-4 col-form-label">Address:</label>
	                    <div class="col-sm-8">
	                        <input id="address" class="form-control" type="text" name="address">
	                    </div>
	                </div>
	                <div class="form-group row">
	                    <label for="address2" class="col-sm-4 col-form-label">Address 2:</label>
	                    <div class="col-sm-8">
	                        <input id="address2" class="form-control" type="text" name="address2">
	                    </div>
	                </div>
	                <div class="form-group row">
	                    <label for="dob" class="col-sm-4 col-form-label">Date of Birth:</label>
	                    <div class="col-sm-8">
	                        <input id="dob" class="form-control" type="date" name="dob" required>
	                    </div>
	                </div>
	                <div class="form-group row">
	                    <label for="gender" class="col-sm-4 col-form-label">Gender:</label>
	                    <div class="col-sm-8">
	                        <select id="gender" class="form-control" name="gender" required>
	                            <option value="Male">Male</option>
	                            <option value="Female">Female</option>
	                            <option value="Other">Other</option>
	                        </select>
	                    </div>
	                </div>
	                <div class="form-group row justify-content-center">
	                    <div class="col-sm-4">
	                        <button type="submit" class="btn btn-primary mt-3 btn-lg">Submit</button>
	                    </div>
	                </div>
	            </form>
	        </div>
	    </div>
	</div>
	

    <script src="js/custom.js"></script>
    
    
</body>
</html>
