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
	          		         <a class="dropdown-item" href="/approvePolicy">Approve Plans</a>
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
    <div class="clear"></div>
    <div id="page-content">
        <section class="flexslider" style="margin-top: 50px;">
            <ul class="slides">
                <li>
                    <img src="images/slider-img.jpg" 
                    />
                    <div class="slide-info">
                        <div class="slide-con">
                            <b>Health Insurance</b>
                            <h3>WellnessCare Group</h3>
                            <p>Offers personalized health insurance solutions focused on enhancing well-being.</p>
                        </div>
                    </div>
                </li>
                <li>
                    <img src="images/slider-img1.jpg" />
                    <div class="slide-info">
                        <div class="slide-con">
                            <b>Health Insurance</b>
                            <h3>PrimeGuard Health Insurance</h3>
                            <p>Offers customized health insurance solutions with a focus on quality care.</p>
                        </div>
                    </div>
                </li>
                <li>
                    <img src="images/slider-img2.jpg" />
                    <div class="slide-info">
                        <div class="slide-con">
                            <b>Health Insurance</b>
                            <h3>CareFirst Health Solutions</h3>
                            <p>Offers tailored health insurance plans designed to meet diverse healthcare needs.</p>
                        </div>
                    </div>
                </li>
            </ul>
        </section>
        <div class="container">
            <div class="row">
                <section class="col-sm-7 col-md-8 col-lg-8">
                    <div class="intro">
                        <h2>Welcome to UnitedHealth Assurance</h2>
                        <p>UnitedHealth Assurance is a leading provider of comprehensive medical health insurance plans, dedicated to ensuring the well-being of individuals and 
                        families across the nation. Offering a wide array of coverage options, UnitedHealth Assurance allows customers to select plans from a curated network of 
                        trusted healthcare providers. Whether it's routine check-ups, specialized treatments, or emergency care, our plans are designed to meet diverse healthcare 
                        needs with flexibility and reliability. Backed by years of industry expertise and a commitment to customer satisfaction, UnitedHealth Assurance strives to 
                        empower individuals with the peace of mind that comes from knowing they have access to quality healthcare when they need it most.</p>
                        <ul class="row">
                            <li class="col-sm-4">
                                <i class="fa fa-life-ring"></i>
                                <h3>24x7 Support</h3>
                                <p>Ever need support. Feel free to contact our mobile for any questions</p>
                            </li>
                            <li class="col-sm-4">
                                <i class="ti-marker-alt"></i>
                                <h3>Easy Claim system</h3>
                                <p>Claims can be submitted online and the application process is quick and easy</p>
                            </li>
                            <li class="col-sm-4">
                                <i class="ti-email"></i>
                                <h3>Get Started with us</h3>
                                <p>Contact us via phone or email and get started now</p>
                            </li>
                        </ul>
                    </div>
                </section>
                <section class="col-sm-5 col-md-4 col-lg-4">
                    <div class="get-quote-form">
                        <h2>Compare  </h2>
                        <form id="get-quote">
                        	<div class="explore-plans">
					            <h3>Explore Our Plans</h3>
					            <p>Discover the right plan for your healthcare needs.</p>
					            <a id="introPlanButton" class="btn-default">Explore Now</a>
					        </div>
                            <div class="testimonial">
					            <h3>What Customers Say</h3>
					            <blockquote>
					                "Choosing UnitedHealth Assurance was the best decision for my family's healthcare needs. They provided excellent support and coverage."
					            </blockquote>
					            <cite>Hannah Reno, Long-time Customer</cite>
					        </div>
					        <div class="contact-info">
					            <h3><strong>Contact Us</strong></h3>
					            <p>For any questions or assistance:</p>
					            <p><strong>Phone:</strong> +1 466-224-4428</p>
					            <p><strong>Email:</strong> info@unitedhealth.com</p>
					        </div>
					        
                        </form>
                    </div>
                </section>
            </div>
        </div>
     
        <section class="product-tab">
            <h2 class="text-center">Our Best Services</h2>
            <div class="container" style="margin-bottom:20px;">
			    <div class="row">
			        <div class="col-sm-3 services-dtl">
			            <span class="fa fa-life-bouy"></span>
			            <h3>24x7 Support</h3>
			            <p>Get round-the-clock assistance via phone or email from our dedicated support team.</p>
			        </div>
			        <div class="col-sm-3 services-dtl" >
			            <span class="ti-pencil"></span>
			            <h3>Easy Claim System</h3>
			            <p>Streamlined process for submitting and tracking insurance claims, ensuring quick resolution.</p>
			        </div>
			        <div class="col-sm-3 services-dtl">
			            <span class="ti-email"></span>
			            <h3>Easy Registration</h3>
			            <p>Simple and straightforward registration process to get you started quickly.</p>
			        </div>
			        <div class="col-sm-3 services-dtl">
			            <span class="fa fa-money"></span>
			            <h3>Easy Installments</h3>
			            <p>Flexible payment options with easy installment plans to suit your financial needs.</p>
			        </div>
			        <div class="col-sm-3 services-dtl">
			            <span class="ti-wallet"></span>
			            <h3>Find the best plans</h3>
			            <p>Explore and compare our comprehensive insurance plans to find the best one for you.</p>
			        </div>
			        <div class="col-sm-3 services-dtl">
			            <span class="fa fa-laptop"></span>
			            <h3>Accessible Online</h3>
			            <p>Manage your insurance policies conveniently online from anywhere, anytime.</p>
			        </div>
			        <div class="col-sm-3 services-dtl">
			            <span class="ti-lock"></span>
			            <h3>Strongly Secure</h3>
			            <p>Highly secure systems to protect your personal and financial information.</p>
			        </div>
			        <div class="col-sm-3 services-dtl">
			            <span class="ti-book"></span>
			            <h3>Easy to Understand</h3>
			            <p>Clear and transparent policy terms and conditions that are easy to comprehend.</p>
			        </div>
			    </div>
			</div>

            </div>
        </section>
        
        
   
    <footer>
        <div class="copyright">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6 col-md-6 col-lg-6">
                        Copyright &copy; 2024 distributed by UnitedHealth Assurance</div>
                    <div class="col-sm-6 col-md-6 col-lg-6 text-right">
                        <a href="#">Terms & Conditions</a>
                        <a href="#">Policy</a>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-hover-dropdown/2.2.1/bootstrap-hover-dropdown.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
    <script src="js/jquery.flexslider-min.js"></script>
    <script src="js/custom.js"></script>
    <script src="js/home.js"></script>
</body>

</html>
