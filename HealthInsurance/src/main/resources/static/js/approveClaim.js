$(document).ready(function () {
	var claimResponse;
	var currentClaimId;
	
	// Load all claims that are pending
	let claimPath = "/api/getClaims"
    function loadClaims() {
        $.get(claimPath, function(response, status) {
			claimResponse= response;
            var tableBody = $("#aproveClaim-table-body");
            tableBody.empty();
            
            $.each(response, function(key, claim) {
                if (claim.status=='PENDING') {
                    var row = $(
                        '<tr>' +
                            '<td class="claim_id">' + claim.id + '</td>' +
                            '<td class="user_id">' + claim.userId + '</td>' +
                            '<td class="policy_id">' + claim.policyId+ '</td>' +
                            '<td class="created_date">' + claim.createdDate + '</td>' +
							'<td class="claim_items">' + '<button class="btn btn-primary claimP">View Claim Items</button>' + '</td>' +
							'<td class="claim_reviewed">' + '<button class="btn btn-success reviewP">Completed</button>' + '</td>' +
                        '</tr>'
                    );
                    tableBody.append(row);
                }
            });
        });
    }
    loadClaims();
    
    
    // Set status for claim
    let statusPath = "/api/setClaimStatus"
	$("#aproveClaim-table-body").on('click', '.reviewP', function() {
		var id = $(this).closest('tr').find('.claim_id').text();
		var userId = $(this).closest('tr').find('.user_id').text();
		var status = "REVIEWED"
		
		var statusObject = { id, userId, status}
		$.ajax({
			    type: "POST",
			    contentType: "application/json",
			    url: statusPath,
			    data: JSON.stringify(statusObject), // Convert the object to JSON string
			    dataType: 'json',
			    success: function(result) {
					console.log(result)
					loadClaims();
			    },
			    error: function(e) {
			        console.log("error: " + e.responseText); // Change alert to display error response text
			    }
		});
	});
	
	// Set approval status for claim items
	var updatePath = "/api/updateClaimItems";
	$(document).on('click', '#updateClaimButton', function() {
        var updatedItems = [];

        $(".documents-list .list-group-item").each(function() {
            var itemId = $(this).find(".docId").text();
            var approvalStatus = $(this).find(".approval-status").val();
            updatedItems.push({ "itemId": itemId, "approvalStatus": approvalStatus });
        });
         
        var updateClaim = { "claimId": currentClaimId, "updatedItems": updatedItems };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: updatePath,
            data: JSON.stringify(updateClaim),
            dataType: 'json',
            success: function(result) {
                
                $("#claimApproveModal").modal('hide');
                
            },
            error: function(e) {
                console.log("error: " + e.responseText);
            }
        });
    });
	
	// Open and set claim item modal
	$("#aproveClaim-table-body").on('click', '.claimP', function() {
        // Clear existing modal data
        var modalBody = $("#claimApproveModal_modalBody");
        modalBody.empty(); // Clear any existing content

        
        var claimId = $(this).closest('tr').find('.claim_id').text();
        var documentsList = $('<div class="documents-list"></div>');
		currentClaimId = claimId;
		
        // Set the claim items based on the claim id
        claimResponse.forEach(function(claim) {
            if (claimId == claim.id) {
                claim.claimItems.forEach(function(doc) {
                    console.log("inside claim response");

                    var attributes = `
                        <table class="table table-bordered">
                            <tr><th>Charge</th><td>$${doc.charge}</td></tr>
                            <tr><th>CPT Code</th><td>${doc.cptcode}</td></tr>
                            <tr><th>Diagnosis</th><td>${doc.diagnosis}</td></tr>
                            <tr><th>Place of Service</th><td>${doc.placeOfService}</td></tr>
                            <tr><th>Service Date Start</th><td>${doc.serviceDateStart}</td></tr>
                            <tr><th>Service Date End</th><td>${doc.serviceDateEnd}</td></tr>
                            <tr><th>Modifiers</th><td>${doc.modifiers}</td></tr>
                            <tr style="display:none;"><th>Document Id</th><td class="docId">${doc.id}</td></tr>
                            <tr><th>Download</th>
                                <td>
                                    <a href='/downloader?filePath=${encodeURIComponent(doc.documentPath)}' download='${doc.documentName}' class='btn btn-primary btn-sm'>Download File: ${doc.documentName}</a>
                                </td>
                            </tr>
                            <tr><th>Approval Status</th>
                                <td>
                                    <div class="form-group">
                                        <select id="approval-status-${doc.id}" class="form-control approval-status">
                                            <option value="approve">Approve</option>
                                            <option value="reject">Reject</option>
                                        </select>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    `;

                    var listItem = $('<div class="list-group-item"></div>');
                    listItem.append(attributes);
                    documentsList.append(listItem);
                });
                
	            var buttonWrapper = $('<div class="text-center"></div>');
	            var updateButton = $('<button id="updateClaimButton" class="btn btn-success update-items">Update</button>');
	            buttonWrapper.append(updateButton);
                documentsList.append(buttonWrapper);
            }
        });

        // Append documents list to modal body
        modalBody.append(documentsList);

        // Show the document modal
        $("#claimApproveModal").modal('show');
    });
});