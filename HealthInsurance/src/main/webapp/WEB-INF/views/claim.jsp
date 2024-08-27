<!DOCTYPE html>
<html lang="en">

<head>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ page isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <style>

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
	
    <div class="container" style="margin-top:220px;">
        <div class="row justify-content-between align-items-center">
            <div class="container">
                <h2>Submit Health Insurance Claim</h2>
                <form id="claimForm">
                    <div class="form-group">
                        <label for="policy-select">Policy Number:</label>
                        <select id="policy-select" name="policyNumber" class="form-control" required>
                            <option disabled selected hidden>Select a Policy Plan</option>
                            <!-- Options will be dynamically populated here -->
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="claimantName">Recipient Name:</label>
                        <input type="text" id="claimantName" name="claimantName" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="dateOfService">Current Date:</label>
                        <input type="date" id="currentDate" name="dateOfService" class="form-control" required>
                    </div>
                    
                    <div id="uploadFieldsContainer">
                        <div class="upload-fields">
                            <div class="form-row">
                                <div class="form-group col-md-2">
                                    <label for="dateOfServiceStart">Date of Service (Start):</label>
                                    <input type="date" class="form-control date-of-service-start" name="dateOfServiceStart[]" required>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="dateOfServiceEnd">Date of Service (End):</label>
                                    <input type="date" class="form-control date-of-service-end" name="dateOfServiceEnd[]" required>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="placeOfService">Place of Service:</label>
                                    <input type="text" class="form-control place-of-service" name="placeOfService[]" required>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="cptCode">CPT Code for Session:</label>
                                    <input type="text" class="form-control cpt-code" name="cptCode[]" required>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="modifiers">Modifiers:</label>
                                    <input type="text" class="form-control modifiers" name="modifiers[]" required>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="diagnosis">Diagnosis:</label>
                                    <input type="text" class="form-control diagnosis" name="diagnosis[]" required>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-2">
                                    <label for="charges">Charge for Service:</label>
                                    <div class="input-group">
                                    
                                    <div class="input-group-prepend">
							                <span class="input-group-text">$</span>
							            </div>
                                    <input type="number" class="form-control charges" name="charges[]" required>
                                    </div>
                                                                
                                    
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="fileInput">Upload Supporting Document:</label>
                                    <input type="file" class="form-control file-input btn" name="fileInput[]" required>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div style="margin-bottom: 70px;">
                    	<button type="button" id="addUploadFieldButton" class="btn btn-primary mr-1">Add Another Service</button>
                    	<button type="button" id="submitClaimButton" class="btn btn-success">Submit Claim</button>
                    </div>
                </form>
                
            </div>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="js/claim.js"></script>
    <script src="js/custom.js"></script>
</body>

</html>
