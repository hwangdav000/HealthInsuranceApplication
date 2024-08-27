$(document).ready(function () {
	
	// Collect the stripe public key from hidden input fields
	var stripePublicKey = $('#stripePublicKey').val()
    var currency = $('#currency').val()
    var planId;
	
	// slider functionality
	var slider = document.getElementById("priceRange");
	var output = document.getElementById("priceValue");
	output.innerHTML = slider.value;
	slider.oninput = function() {
		output.innerHTML = this.value;
	}
	
	
	// populate all plans at start
	let providerPlanPath = "/api/getPolicyPlans"
    $.get(providerPlanPath, function(response, status) {
        if (status === "success") {
            providerJson = response;
            renderCards(response);
        } else {
            console.error("Error fetching data", status);
        }
    });
	
	// render stars (rating) on the cards
    function renderStars(rating) {
        const stars = [];
        const totalStars = 5;
        const fullStars = Math.floor(rating);
        const hasHalfStar = rating - fullStars >= 0.5;
		
		// using bootstrap icons
        for (let i = 0; i < fullStars; i++) {
            stars.push('<i class="bi bi-star-fill text-warning"></i>');
        }

        if (hasHalfStar) {
            stars.push('<i class="bi bi-star-half text-warning"></i>');
        }

        const emptyStars = totalStars - fullStars - (hasHalfStar ? 1 : 0);
        for (let i = 0; i < emptyStars; i++) {
            stars.push('<i class="bi bi-star text-warning"></i>');
        }

        return stars.join('');
    }

	// render cards which contain the policy plan
    function renderCards(plans) {
        const container = $('#cards-container');
        container.empty(); // Clear the container

		// for each plan create respective card
		// will store additional information in the card through hidden
        $.each(plans, function(index, plan) {
            const card = $(`
                <div class="card card-custom h-100">
				    <img class="card-img-top plan_image" src="${plan.imageURL}" alt="${plan.name}" style="object-fit: cover;">
				    <div class="card-body card-body-custom d-flex flex-column">
				        <h5 class="card-title plan_name">${plan.name}</h5>
				        <p class="card-text"><strong>Metal Tier:</strong> <span class="plan_metalTier">${plan.metalTier}</span></p>
				        <p class="card-text"><strong>Type:</strong> <span class="plan_type">${plan.type}</span></p>
				        <p class="card-text"><strong>Base Premium:</strong> $<span class="plan_premium">${plan.basePremium}</span></p>
				        <input type="hidden" class="policy_id" value="${plan.policyId}">
				        <input type="hidden" class="coInsurance" value="${plan.coInsurance}">
				        <input type="hidden" class="coPay" value="${plan.coPay}">
				        <input type="hidden" class="coverageLength" value="${plan.coverageLength}">
				        <input type="hidden" class="deductible" value="${plan.deductible}">
				        <input type="hidden" class="outOfPocket" value="${plan.outOfPocket}">
				        <input type="hidden" class="planLimit" value="${plan.planLimit}">
				        <input type="hidden" class="planDescription" value="${plan.description}">
				        
				        <div class="mt-1">
				            <strong>Rating:</strong>
				            <div class="stars-container">
				                ${renderStars(plan.rating)}
				            </div>
				        </div>
				        <a href="#" class="btn btn-primary mt-auto buyPlan">View Plan</a>
				        <div class="policy_id" style="display: none;">${plan.policyId}</div>
				    </div>
				</div>

            `);
            container.append(card);
        });
    }
    
    
	// This will open/populate modal which contains more details about plan
	$('#cards-container').on('click', '.buyPlan', function() {
		
		//Check that user is logged in
		var userName = $('#username').text();
			
		if (!userName) {
			alert("Please Login to Purchase Plans");
			return false
		}
		
		// Retrieve plan details from the clicked card
	    var card = $(this).closest('.card');
	    var planName = card.find('.plan_name').text().trim();
	    var metalTier = card.find('.plan_metalTier').text().trim();
	    var type = card.find('.plan_type').text().trim();
	    var basePremium = card.find('.plan_premium').text().trim();
	    var planImage =  card.find('.plan_image').attr('src');
	    
	    var coInsurance = card.find('.coInsurance').val();
	    var coPay = card.find('.coPay').val();
	    var coverageLength = card.find('.coverageLength').val();
	    var deductible = card.find('.deductible').val();
	    var outOfPocket = card.find('.outOfPocket').val();
	    var planLimit = card.find('.planLimit').val();
	    var planDescription = card.find('.planDescription').val();
	    
	    planId = card.find('.policy_id').val();
	    var amount= basePremium;
	  	
		// set parameters of modal
		$('#purchase_image').attr('src', planImage);
		$("#purchase_description").text(planDescription)
		
		$("#purchase_name").text(planName);
        $("#purchase_metal").text(metalTier);
        $("#purchase_type").text(type);
        $("#purchase_premium").text(basePremium);
        
        $("#purchase_coInsurance").text(coInsurance);
        $("#purchase_coPay").text(coPay);
        $("#purchase_coverageLength").text(coverageLength);
        $("#purchase_deductible").text(deductible);
        $("#purchase_outOfPocket").text(outOfPocket);
        $("#purchase_planLimit").text(planLimit);
		
		$('#purchasePlanModal').toggle();			
	});
	
	// open payment modal for stripe
	$('#purchase_confirm').click(function() {
		// check that start date is filled
		if (!$("#purchase_startDate").val()) {
			alert('please fill out start date')
			return -1;
		}
		
    	
    	$('#purchasePlanModal').hide();
    	console.log('purchase confirmed');
    	
    	
    	var planName;
    	var premium; 
    	// look for plan in providerJson
    	console.log("provider json" ,planId)
	    $.each(providerJson, function(index, plan){
			if (plan.policyId == planId) {
				console.log("inside ", plan)
				premium = plan.basePremium;
				planName = plan.name;
			}
			
		})
			
    	// change the var in plan modal
    	$('#policyPlan').text(planName)
    	$('#policyCost').text(premium)
    	$('#paymentModal').show(); 
	});
	
	// Handling Stripe payment
	// Create a Stripe client
    var stripe = Stripe(stripePublicKey);
	console.log("in ", stripePublicKey)
	
    // Create an instance of Elements
    var elements = stripe.elements();

    // Custom styling can be passed to options when creating an Element.
    var style = {
        base: {
            color: '#32325d',
            fontFamily: '"Helvetica Neue", Helvetica, sans-serif',
            fontSmoothing: 'antialiased',
            fontSize: '16px',
            '::placeholder': {
                color: '#aab7c4'
            }
        },
        invalid: {
            color: '#fa755a',
            iconColor: '#fa755a'
        }
    };

    // Create an instance of the card 
    var card = elements.create('card', {style: style, hidePostalCode: true});
    card.mount('#card-element');

    // Handle validation errors
    card.on('change', function (event) {
        var displayError = document.getElementById('card-errors');
        if (event.error) {
            displayError.textContent = event.error.message;
        } else {
            displayError.textContent = '';
        }
    });

    // Handle form submission.
    $('#payment-form').on('submit', function (event) {
        event.preventDefault();

		// generate stripe token
        stripe.createToken(card).then(function (result) {
            if (result.error) {
                
                var errorElement = document.getElementById('card-errors');
                errorElement.textContent = result.error.message;
            } else {
                
                stripeTokenHandler(result.token);
            }
        });
    });

	// Sends stripe token to backend and confirms payment
    function stripeTokenHandler(token) {
	    var email = $('#email').val();
	    var tokenId = token.id;
	    var amount; // basePremium
	    var startDate= $("#purchase_startDate").val(); // get this from modal
	    var coverageLength;
	    var planName;
	    
	    // look for corresponding plan
	    $.each(providerJson, function(index, plan){
			if (plan.policyId == planId) {
				amount = plan.basePremium;
				coverageLength = plan.coverageLength;
				planName = plan.name;
			}
		})

	    $.ajax({
	        type: 'POST',
	        url: '/charge',
	        contentType: 'application/json',
	        data: JSON.stringify({
	            currency: currency,
	            stripeEmail: email,
	            stripeToken: tokenId,
	            amount, 
	            startDate,
	            coverageLength,
	            planName,
	            planId
	        }),
	        success: function (data) {
	            // Here handle success or failure responses accordingly
	            console.log(data);
	            window.location.href = '/result';
	            
	        },
	        error: function (error) {
	            // Handle errors
	            console.error('Error:', error);
	            window.location.href = '/failure';
	        }
	    });
	}
		
	// handle basic modal functionalities
	$('#purchase_close').click(function() {	
		$('#purchasePlanModal').hide();
	})
	
	$('#payment_close').click(function() {	
		$('#paymentModal').hide();
	})
	
	////////////////////////////////////
	// handle filtering functionality
	
	// gets all the policy plans when reset button is clicked
	$("#resetBtn").click(function() {
		var planSearch = {
				    "searchString": "",
				    "ratings": "",
				    "amount" : "",
				    "tiers" : "",
				    "planTypes" : "",
				    "status": "RESET"
				};
				
		$.ajax({
			    type: "POST",
			    contentType: "application/json",
			    url: "/api/filterPlans",
			    data: JSON.stringify(planSearch), // Convert the object to JSON string
			    dataType: 'json',
			    success: function(response) {
					console.log(response);
			        renderCards(response);
			    },
			    error: function(e) {
			        console.log("error: " + e.responseText);
			    }
			});
	});
		
	// filters based on options chosen
	$("#filterBtn").click(function() {
		
			// get checked fields
			var ratings = "";
			var lengthChecked= $(".star_rating:checked").length;
			var count1 = 0;
			var count2 = 0;
			var count3 = 0;
			$(".star_rating:checked").each(function(key, value) {
			    ratings += $(this).val();
			    if (count1 < lengthChecked - 1) {
			        ratings += ",";
			    }
			    count1++;
			});
			
			
			var tiers = "";
			var lengthChecked2 = $(".metal_tier:checked").length;
			$(".metal_tier:checked").each(function(key, value) {
				
				tiers += $(this).val();
				if (count2 < lengthChecked2 - 1) {
			        tiers += ",";
			    }
			    count2++;
			})
			
			var planTypes = "";
			var lengthChecked3 = $(".plan_type:checked").length;
			$(".plan_type:checked").each(function(key, value) {
				
				planTypes += $(this).val();
				if (count3 < lengthChecked3 - 1) {
			        planTypes += ",";
			    }
			    count3++;
			})
			
			var searchString = $("#filterPlan").val();
			var amount = $('#priceValue').text()
			var planSearch = {
			    searchString,
			    ratings,
			    amount,
			    tiers,
			    planTypes,
			    "status": "FILTER"
			};
			
			$.ajax({
			    type: "POST",
			    contentType: "application/json",
			    url: "/api/filterPlans",
			    data: JSON.stringify(planSearch), // Convert the object to JSON string
			    dataType: 'json',
			    success: function(response) {
					console.log(response);
			        renderCards(response);
			    },
			    error: function(e) {
			        console.log("error: " + e.responseText);
			    }
			});
		
	})
});