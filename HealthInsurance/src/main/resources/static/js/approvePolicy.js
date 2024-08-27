$(document).ready(function () {
	
	// Load all the policies that are pending
    let approvePath = "/api/getPolicyIssues"
    function loadPolicyIssues() {
        $.get(approvePath, function(response, status) {
            var tableBody = $("#PA-table-body");
            tableBody.empty();
            
            $.each(response, function(key, issue) {
                if (issue.status=='PENDING') {
                    var row = $(
                        '<tr>' +
                            '<td class="policy_id">' + issue.id + '</td>' +
                            '<td class="user_id">' + issue.userId + '</td>' +
                            '<td class="policy_name">' + issue.plan.name + '</td>' +
                            '<td class="policy_plan_id" style="display: none;">' + issue.plan.policyId+ '</td>' +
                            '<td class="documents">' + '<button class="btn btn-primary documentsP">View Documents</button>' + '</td>' +
							'<td class="policy_approve">' + '<button class="btn btn-success approveP">Approve</button>' + '</td>' +
							'<td class="policy_reject">' + '<button class="btn btn-danger rejectP">Reject</button>' + '</td>' +
                            
                        '</tr>'
                    );
                    tableBody.append(row);
                }
            });
        });
    }
    loadPolicyIssues();
	
	// Set policy issue to approve
	let statusPath = "/api/setPolicyStatus"
	$("#PA-table-body").on('click', '.approveP', function() {

		var id = $(this).closest('tr').find('.policy_id').text();
		var userId = $(this).closest('tr').find('.user_id').text();
		var policyId = $(this).closest('tr').find('.policy_plan_id').text();
		var status = "APPROVED"
		
		var statusObject = { id, userId, policyId, status}
		$.ajax({
			    type: "POST",
			    contentType: "application/json",
			    url: statusPath,
			    data: JSON.stringify(statusObject), // Convert the object to JSON string
			    dataType: 'json',
			    success: function(result) {
					console.log(result)
					loadPolicyIssues();
			    },
			    error: function(e) {
			        console.log("error: " + e.responseText); // Change alert to display error response text
			    }
		});
	});
	
	// Set policy issue to reject
	$("#PA-table-body").on('click', '.rejectP', function() {

		var id = $(this).closest('tr').find('.policy_id').text();
		var userId = $(this).closest('tr').find('.user_id').text();
		var policyId = $(this).closest('tr').find('.policy_plan_id').text();
		var status = "REJECT"
		
		var statusObject = { id, userId, policyId, status}
		$.ajax({
			    type: "POST",
			    contentType: "application/json",
			    url: statusPath,
			    data: JSON.stringify(statusObject), // Convert the object to JSON string
			    dataType: 'json',
			    success: function(result) {
					loadPolicyIssues();
			    },
			    error: function(e) {
			        console.log("error: " + e.responseText); // Change alert to display error response text
			    }
		});
	});
	
	// Open the Document Modal for corresponding user
	$("#PA-table-body").on('click', '.documentsP', function() {
		
		// clear existing modal data
	    var modalBody = $("#documentModal_modalBody");
	    modalBody.empty(); 
	
	    var userId = $(this).closest('tr').find('.user_id').text();
	    
	    // get related documents for the user
		let getDocumentsPath = "/api/getDocuments/" + userId;
	    $.ajax({
	        url: getDocumentsPath,
	        type: 'GET',
	        success: function(response) {
	            
	            // create a download link for each document
	            var documentsList = $('<ul class="list-group"></ul>');
	
	            response.forEach(function(doc) {
					
	                var fileNameWithExtension = doc.documentName + '.' + doc.extensionType;
	                var listItem = $('<li class="list-group-item"></li>');
	
	                // Create download link
	                var downloadLink = $('<a></a>')
	                    .attr('href', '/downloader?filePath=' + encodeURIComponent(doc.documentPath))
	                    .attr('download', fileNameWithExtension)
	                    .addClass('btn btn-primary btn-sm') // Bootstrap button style
	                    .text('Download File: ' + doc.documentName);
	
	                listItem.append(downloadLink);
	                documentsList.append(listItem);
	            });
	
	            modalBody.append(documentsList);
	        },
	        error: function(error) {
	            console.log("Error fetching documents:", error);
	            alert("Error fetching documents.");
	        }
    	});

    $("#DocumentModal").modal('show');
	});
	
	// modal close
	$('#dm-close').click(function() {
		$("#DocumentModal").toggle()
	})
	
});