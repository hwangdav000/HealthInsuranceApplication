$(document).ready(function () {
	var claimId;
	
	// get # of claims , use to set name of claim Item document
	var claimCountPath = "/api/getClaimCount"
	$.get("/api/getClaimCount", function(response, status) {
		claimId = response
	})
	
	// get the policy issues for the User (using principal)
	var policyPath = "/api/getPolicyIssues2"
	$.get(policyPath, function(response, status) {
		var policySelect =$('#policy-select') 
		console.log(response)
		$.each(response, (key, value) => {
			 var optionHTML = '<option>' + value.id + ' ' + value.plan.name + '</option>';
			 
			 policySelect.append(optionHTML);
		})
	});
	
	// sumbit claim	
	$('#submitClaimButton').on('click', function(event) {
	    event.preventDefault();
	
	    var formData = new FormData();
	    var validExtensions = ['jpg', 'jpeg', 'png', 'pdf'];
	    var maxFileSize = 5242880; // 5mb
	    var valid = true;
	    var claimData = {};
	
	    // get info for claim files
	    $('.file-input').each(function(index, element) {
	        var file = $(element)[0].files[0];
	        var fileExtension = file.name.split('.').pop().toLowerCase();
	        var fileName = file.name;
	        var fileSize = file.size;
	
	        // Validate file extension
	        if ($.inArray(fileExtension, validExtensions) === -1) {
	            alert('Invalid file type. Only JPG, JPEG, PNG, and PDF files are allowed: ' + fileName);
	            valid = false;
	            return false; // Stop further processing
	        }
	
	        if (fileSize > maxFileSize) {
	            alert('File Size exceeded Limit (5mb): ' + fileName);
	            valid = false;
	            return false;
	        }
	
	        // Add file and file name to FormData object
	        formData.append('files', file);
	        formData.append('fileExtensions', fileExtension);
	        claimData['fileExtensions'] = claimData['fileExtensions'] ? [...claimData['fileExtensions'], fileExtension] : [fileExtension];
	        formData.append('claimId', claimId);
	    });
	
	    if (!valid) {
	        return false;
	    }
	
	    // first upload the claim item documents to the database
	    $.ajax({
	        url: '/uploaderClaim',
	        type: 'POST',
	        data: formData,
	        processData: false,
	        contentType: false,
	        success: function(response) {
	            console.log(response);
	            console.log("AFTER UPLOADER CLAIM");
	
	            // now need to create the claim Item documents
	            // get information of the multiple claim items
	            var elementClasses = [
	                'date-of-service-start',
	                'date-of-service-end',
	                'place-of-service',
	                'cpt-code',
	                'modifiers',
	                'diagnosis',
	                'charges'
	            ];
	
	            elementClasses.forEach(function(className) {
	                var elements = $('.' + className);
	                elements.each(function(index, element) {
	                    var value = $(element).val();
	
	                    if (value !== null && value !== undefined && value !== '') {
	                        if (!claimData[className]) {
	                            claimData[className] = [];
	                        }
	                        claimData[className].push(value);
	                    } else {
	                        console.log('No value found for class:', className, 'Index:', index);
	                    }
	                });
	            });
	
	            // get basic info
	            claimData["claimantName"] = $("#claimantName").val();
	            claimData["currentDate"] = $("#currentDate").val();
	            claimData["claimId"] = claimId;
	
	            var policyIdString = $("#policy-select").val();
	            if (policyIdString === '') {
	                alert("please specify policy");
	                return;
	            }
	
	            var policyId = policyIdString.split(' ')[0];
	            claimData["policyId"] = policyId;
	
	            console.log(claimData);
	            $.ajax({
	                type: 'POST',
	                url: '/api/saveClaim',
	                contentType: 'application/json',
	                data: JSON.stringify(claimData),
	                success: function (data) {
	                    console.log(data);
	                    alert("Claim submitted");
	                },
	                error: function (error) {
	                    console.error('Error:', error);
	                }
	            });
	
	        },
	        error: function(error) {
	            alert("failure");
	            console.log("Error uploading files:", error);
	        }
	    });
	});

	
	
	// Add additional claim items
	$('#addUploadFieldButton').click(function() {
        var newUploadFields = `
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
                        <input type="number" class="form-control charges" name="charges[]" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="fileInput">Upload Supporting Document:</label>
                        <input type="file" class="form-control file-input btn" name="fileInput[]" required>
                    </div>
                </div>
            </div>`;
        $('#uploadFieldsContainer').append(newUploadFields);
    });
        
});