<!DOCTYPE html>
<html lang="en">

<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1" %> <%@ page isELIgnored="false" %> <%@ taglib
  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
  prefix="security" uri="http://www.springframework.org/security/tags" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Insurance - Press</title>
    <link rel="shortcut icon" href="images/favicon.png" />
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css" rel="stylesheet" type="text/css">
    <link href="css/icons.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="css/flexslider.css " />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<!-- ONLY IF THE USER IS NOT LOGGED IN-->
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
 
	<div class="container" style="margin-top: 200px;>
	    <div class="text-center mb-3">
		    <div class="d-flex justify-content-center">
	          <div id="Plan-table" class="mt-3">
	            <table class="table table-striped">
	              <thead>
	                <tr>
	                  <th>Policy Id</th>
	                  <th>Policy Name</th>
	                  <th>Status</th>
	                </tr>
	              </thead>
	              <tbody id="Plan-table-body"></tbody>
	            </table>
	          </div>
	        </div>
	    </div>
	    
        <div class="text-center mb-3">
            <h3>Upload Documents</h3>
        </div>
        <form id="uploadForm">
		    <div class="container">
		    	<div id="uploadFieldsContainer">
			        <div class="row mb-3 justify-content-center field-row">
			            <div class="col-md-5">
			                <label for="document1" class="form-label">Identification Document:</label>
			                <input type="text" class="form-control document-name" name="documentName[]" id="document1" placeholder="Document Name" />
			            </div>
			            <div class="col-md-5">
			                <input type="file" class="form-control file-input btn" name="fileInput[]" id="fileInput1">
			            </div>
			        </div>
			
			        <div class="row mb-3 justify-content-center field-row">
			            <div class="col-md-5">
			                <label for="document2" class="form-label">Proof of Income:</label>
			                <input type="text" class="form-control document-name" name="documentName[]" id="document2" placeholder="Document Name" />
			            </div>
			            <div class="col-md-5">
			                <input type="file" class="form-control file-input btn" name="fileInput[]" id="fileInput2">
			            </div>
			        </div>
			
			        <div class="row mb-3 justify-content-center field-row">
			            <div class="col-md-5">
			                <label for="document3" class="form-label">Proof of Residency:</label>
			                <input type="text" class="form-control document-name" name="documentName[]" id="document3" placeholder="Document Name" />
			            </div>
			            <div class="col-md-5">
			                <input type="file" class="form-control file-input btn" name="fileInput[]" id="fileInput3">
			            </div>
			        </div>
				</div>
				
				<div style="margin-bottom: 70px; margin-left: 92px;">
                    	<button type="button" id="addUploadFieldButton" class="btn btn-primary">Add Additional Documentation</button>
                    	<button type="button" id="uploadButton" class="btn btn-success">Upload All Documents</button>
                </div>

		    </div>
		</form>

    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-hover-dropdown/2.2.1/bootstrap-hover-dropdown.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
    <script src="js/jquery.flexslider-min.js"></script>
    <script src="js/custom.js"></script>
    <script src="js/account.js"></script>
</body>

</html>
