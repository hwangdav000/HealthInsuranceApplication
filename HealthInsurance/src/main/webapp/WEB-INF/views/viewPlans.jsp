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
    
    <script src="https://js.stripe.com/v3/"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-hover-dropdown/2.2.1/bootstrap-hover-dropdown.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.4.1/font/bootstrap-icons.min.css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    
    <script src="js/jquery.flexslider-min.js"></script>
    <script src="js/custom.js"></script>
    <script src="js/viewPlans.js"></script>
  <style>
        .card-container-custom {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: center;
        }
        .card-custom {
            flex: 0 1 300px; /* Fixed width for each card */
            height: 480px !important; /* Fixed height for each card */
            margin: 10px;
            box-sizing: border-box; /* Ensures padding and border are included in the width and height */
        }
        .card-body-custom {
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }
        .card-img-top {
            height: 150px;
            object-fit: cover;
        }
        .stars-container {
            cursor: pointer;
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
			        <p style="margin: 5px 0; margin-left:27px">Call Us Now <b>+466 (224) 4428</b></p>
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

<div class="row" style="margin-right: 0px !important; margin-top: 250px">
  <div class="col-2 border rounded" style="margin-left:50px;padding:25px">
    <h5>Filter Options</h5>
            
            <div class="form-group">
			    <label for="filterPlan">Plan Name:</label>
			    <input type="text" class="form-control plan_filter" id="filterPlan" name="filterPlan" placeholder="Search by plan">
			</div>
            
            <div class="form-group">
                <label>Star Rating:</label><br>
                <div class="form-check form-check-inline">
                    <input type="checkbox" class="form-check-input star_rating" id="1_star_rating" value="1">
                    <label class="form-check-label" for="1_star_rating">1</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="checkbox" class="form-check-input star_rating" id="2_star_rating" value="2">
                    <label class="form-check-label" for="2_star_rating">2</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="checkbox" class="form-check-input star_rating" id="3_star_rating" value="3">
                    <label class="form-check-label" for="3_star_rating">3</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="checkbox" class="form-check-input star_rating" id="4_star_rating" value="4">
                    <label class="form-check-label" for="4_star_rating">4</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="checkbox" class="form-check-input star_rating" id="5_star_rating" value="5">
                    <label class="form-check-label" for="5_star_rating">5</label>
                </div>
            </div>
            
            <div class="form-group">
                <label for="priceRange">Range:</label>
                <div class="slidecontainer">
                    <input type="range" min="1" max="400" value="400" class="slider" id="priceRange">
                    <p>Price: $<span id="priceValue">400</span></p>
                </div>
            </div>
            
            <div class="form-group">
                <label>Plan Type:</label><br>
                <div class="form-check">
                    <input type="checkbox" class="form-check-input plan_type" id="HMO" value="HMO">
                    <label class="form-check-label" for="HMO">HMO</label><br>
                    
                    <input type="checkbox" class="form-check-input plan_type" id="PPO" value="PPO">
                    <label class="form-check-label" for="PPO">PPO</label><br>
                    
                    <input type="checkbox" class="form-check-input plan_type" id="POS" value="POS">
                    <label class="form-check-label" for="POS">POS</label><br>
                    
                    <input type="checkbox" class="form-check-input plan_type" id="EPO" value="EPO">
                    <label class="form-check-label" for="EPO">EPO</label><br>
                </div>
            </div>
            
            <div class="form-group">
                <label>Metal Tier:</label><br>
                <div class="form-check">
                    <input type="checkbox" class="form-check-input metal_tier" id="BRONZE" value="BRONZE">
                    <label class="form-check-label" for="BRONZE">BRONZE</label><br>
                    
                    <input type="checkbox" class="form-check-input metal_tier" id="SILVER" value="SILVER">
                    <label class="form-check-label" for="SILVER">SILVER</label><br>
                    
                    <input type="checkbox" class="form-check-input metal_tier" id="GOLD" value="GOLD">
                    <label class="form-check-label" for="GOLD">GOLD</label><br>
                    
                    <input type="checkbox" class="form-check-input metal_tier" id="PREMIUM" value="PREMIUM">
                    <label class="form-check-label" for="PREMIUM">PREMIUM</label><br>
                </div>
            </div>
            
            <button style="margin-top: 25px;" class="btn btn-primary" type="button" id="filterBtn">FILTER</button>
            <button style="margin-top: 25px;" class="btn btn-danger" type="button" id="resetBtn">RESET</button>
        </div>

  <div class="col-7" style="margin-left:50px;">
    <div id="listProviders">

   	 	<div id="cards-container" class="card-container card-container-custom"></div>

    </div>
  </div>
</div>

    
    
 <!-- Modal -->
<div class="modal" id="purchasePlanModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Policy Details</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                <div class="text-center mb-3">
                    <!-- Image or icon of the policy -->
                    <img id="purchase_image" src="" alt="Policy Image" class="img-fluid" style="max-width: 400px; height: auto;">
                </div>
                <!-- Description of the policy -->
                <p class="text-center"><strong>Description:</strong> <span id="purchase_description"> </span></p>
                <div class="row">
                    <div class="col-md-6">
                        <!-- Policy details -->
                        <p><strong>Provider:</strong> <span id="purchase_name"></span></p>
                        <p><strong>Metal Tier:</strong> <span id="purchase_metal"></span></p>
                        <p><strong>Type:</strong> <span id="purchase_type"></span></p>
                        <p><strong>Base Premium:</strong> $<span id="purchase_premium"></span></p>
                        <p><strong>Co-Insurance:</strong> <span id="purchase_coInsurance"></span>%</p>
                        <p><strong>Co-Pay:</strong> <span id="purchase_coPay"></span>%</p>
                    </div>
                    <div class="col-md-6">
                        <!-- More policy details -->
                        <p><strong>Coverage Length:</strong> <span id="purchase_coverageLength"></span> months</p>
                        <p><strong>Deductible:</strong> <span id="purchase_deductible"></span>%</p>
                        <p><strong>Out of Pocket:</strong> $<span id="purchase_outOfPocket"></span></p>
                        <p><strong>Plan Limit:</strong> $<span id="purchase_planLimit"></span></p>
                        <div class="form-group">
                            <label for="purchase_startDate"><strong>Start Date of Plan: </strong></label>
                            <input class="form-control" type="date" id="purchase_startDate"/>
                        </div>
                    </div>
                </div>
                <div class="text-right mt-3">
                    <button id="purchase_confirm" class="btn btn-primary mr-2">Purchase Policy</button>
                    <button type="button" id="purchase_close" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
    </div>
</div>

	<!-- Payment Modal -->
	<div class="modal" id="paymentModal">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">View Plans</h4>
	      </div>
	
	      <!-- Modal body -->
	      <div class="modal-body" id="payment_modalBody">        
		    <!--hero section-->
				<section class="py-5">
				    <div class="container">
				        <div class="row">
				            <div class="col-lg-6 col-md-8 col-12 my-auto mx-auto">
				                <h1>Stripe Plan Charge</h1>
				                <p class="lead mb-4">Please fill the form below to complete the order payment</p>
				                <div class="card mb-4">
				                    <div class="card-body">
				                        <h5 id="policyPlan">Plan</h5>
				                        <p class="lead">
										    <span>$</span>
										    <span id="policyCost">9.99</span>
										</p>
				                    </div>
				                </div>
				                <form action="#" id="payment-form" method="post">
				                    <div class="form-group">
				                        <input class="form-control" id="email" name="email" placeholder="Email Address" type="email" required>
				                    </div>
				                    <div class="form-group">
				                        <label class="font-weight-medium" for="card-element">Enter credit or debit card below</label>
				                        <div id="card-element"><!-- A Stripe Element will be inserted here. --></div>
				                    </div>
				                    <!-- Used to display Element errors. -->
				                    <div class="text-danger w-100" id="card-errors" role="alert"></div>
				                    <div class="form-group pt-2">
				                    	<div style='margin-top:20px'>
									    <div style="display: inline-block; margin-right: 10px;">
									        <button class="btn btn-primary btn-block" id="submitButton" type="submit">Pay With Your Card</button>
									    </div>
									    <div style="display: inline-block;">
									        <button type="button" id="payment_close" class="btn btn-danger" data-dismiss="modal">Cancel</button>
									    </div>
									</div>
				                        
				                        <div class="small text-muted mt-2">
				                            Pay securely with Stripe. By clicking the button above, you agree to our
				                            <a target="_blank" href="#">Terms of Service</a>,
				                            <a target="_blank" href="#">Privacy</a> and
				                            <a target="_blank" href="#">Refund</a> policies.
				                        </div>
				                    </div>
				                </form>
				            </div>
				        </div>
				    </div>
				</section>  
		  </div>	
	    </div>          
	</div>
	<div id="hiddenValues" style="display: none;">
    <input type="hidden" id="stripePublicKey" value="<%= request.getAttribute("stripePublicKey") %>">
    <input type="hidden" id="currency" value='<%= request.getAttribute("currency") %>'>
	</div>
	
</body>

</html>
