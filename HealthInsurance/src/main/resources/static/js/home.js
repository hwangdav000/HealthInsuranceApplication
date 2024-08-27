$(document).ready(function () {

	$('#introPlanButton').on('click', function(event) {
		
		var userName = $('#username').text();
		
		// redirect based on whether the user is logged in or not		
		if (!userName) {
			window.location.href = '/introPlans';
			return false
		} else {
			window.location.href = '/viewPlans';
		}
    });
});