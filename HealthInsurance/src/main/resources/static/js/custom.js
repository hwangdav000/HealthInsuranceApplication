/************************************************
Template Name: Insurance-Press Boostrap Template
Author: BoostrapMart
Develop By: BoostrapMart
Developer URL: http://info@bootstrapmart.com
************************************************/

// jQuery to collapse the navbar on scroll
function collapseNavbar() {
    if ($(window).scrollTop() > 50) {
        $(".navbar-fixed-top").addClass("top-nav-collapse");
    } else {
        $(".navbar-fixed-top").removeClass("top-nav-collapse");
    }
}

$(window).scroll(collapseNavbar);
$(document).ready(collapseNavbar);


// Closes the Responsive Menu on Menu Item Click
$('.navbar-collapse ul li a').click(function() {
  if ($(this).attr('class') != 'dropdown-toggle active' && $(this).attr('class') != 'dropdown-toggle') {
    $('.navbar-toggle:visible').click();
  }
});



$(window).load(function(){
    
	
    
	// Flex Slider
	$('.flexslider').flexslider({
		animation: "fade",
		start: function(slider){
		  $('body').removeClass('loading');
		}
	});
    
    
    
});
