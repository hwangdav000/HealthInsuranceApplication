$(document).ready(function () {
	var claimResponse;
	var currentClaimId;
	
	// load all the claims that have been reviewed (completed)
	let claimPath = "http://localhost:8282/api/getClaims/"
    function loadClaims() {
        $.get(claimPath, function(response, status) {
			claimResponse= response;
            var tableBody = $("#claim-table-body");
            tableBody.empty();
            console.log(response)
            $.each(response, function(key, claim) {
                if (claim.status=='REVIEWED') {
                    var row = $(
                        '<tr>' +
                            '<td class="claim_id">' + claim.id + '</td>' +
                            '<td class="user_id">' + claim.userId + '</td>' +
                            '<td class="policy_id">' + claim.policyId+ '</td>' +
                            '<td class="created_date">' + claim.createdDate + '</td>' +
							'<td class="claim_items">' + '<button class="btn btn-primary claimP">View Claim Items</button>' + '</td>' +
                        '</tr>'
                    );
                    tableBody.append(row);
                }
            });
        });
    }
    loadClaims();
	
	// Show the Claim Items Modal
	$("#claim-table-body").on('click', '.claimP', function() {

        var modalBody = $("#viewClaimModal_modalBody");
        modalBody.empty(); // Clear any existing content

        
        var claimId = $(this).closest('tr').find('.claim_id').text();
        var documentsList = $('<div class="documents-list"></div>');
		currentClaimId = claimId;
        
        // Show the attributes for the claim items based on claim id
        claimResponse.forEach(function(claim) {
            if (claimId == claim.id) {
                claim.claimItems.forEach(function(doc) {

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
                            <tr><th>Status</th><td>${doc.status}</td></tr>
                        </table>
                    `;

                    var listItem = $('<div class="list-group-item"></div>');
                    listItem.append(attributes);

                    documentsList.append(listItem);
                });
                
                
            }
        });

        modalBody.append(documentsList);
        $("#viewClaimModal").modal('show');
    });  
	
});