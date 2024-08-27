$(document).ready(function () {
	
	//Get the policy plan for the user (principal)
    var policyPath = "/api/getPolicyIssues2"
    $.get( policyPath, function(response, status) {
		var policyBody = $('#Plan-table-body');
		policyBody.empty();
		
		$.each(response, (key, issue) => {
			var row = $(
                        '<tr>' +
                            '<td class="issue_id">' + issue.id + '</td>' +
                            '<td class="issue_id">' + issue.plan.name + '</td>' +
                            '<td class="status_id">' + issue.status + '</td>' +
                        '</tr>'
                    );
                    policyBody.append(row);
		})	
	});
	
	// add upload document fields
	let maxUploadFields = 6;
	 $('#addUploadFieldButton').click(function() {
		if ($('.field-row').length < maxUploadFields) {
			var newUploadField = `
			<div class="row mb-3 justify-content-center field-row">
		            <div class="col-md-5">
		                <label for="document" class="form-label">Additional Documentation:</label>
		                <input type="text" class="form-control document-name" name="documentName[]" placeholder="Document Name">
		            </div>
		            <div class="col-md-5">
		                <input type="file" class="form-control file-input btn" name="fileInput[]">
		            </div>
		    </div>
		 	`;
	    	$('#uploadFieldsContainer').append(newUploadField);	
		} else {
			alert("Can upload maximum of " + maxUploadFields + " documents")
		}
	    
   });
   
   // upload all documents
   $('#uploadButton').on('click', function(event) {
		var formData = new FormData();
    	var validExtensions = ['jpg', 'jpeg', 'png', 'pdf'];
    	var maxFileSize = 5242880; // 5 mb
    	var valid = true
    	
    	// get file attributes
    	$('.file-input').each(function(index, element) {
			var file = $(element)[0].files[0];
			var filename = $(this).closest('.field-row').find('.document-name').val();
			var fileExtension = file.name.split('.').pop().toLowerCase();
			var fileSize = file.size;
			
			// Validate file extension
            if ($.inArray(fileExtension, validExtensions) == -1) {
                alert('Invalid file type. Only JPG, JPEG, PNG and PDF files are allowed: ' + filename);
				valid = false                
                return false; // Stop further processing
            }
            
            if (fileSize > maxFileSize) {
				alert('File Size exceeded Limit (5mb): ' + filename)
				valid = false
				return false;
			}

            // Add file and file name to FormData object
            formData.append('fileNames', filename);
            formData.append('files', file);
            formData.append('fileExtensions', fileExtension);
		})
		if (!valid) {
			return false; 
		}
		
	    $.ajax({
	        url: '/uploader',
	        type: 'POST',
	        data: formData,
	        processData: false,
	        contentType: false,
	        success: function(response) {
				alert("Files have been uploaded")
	            
	        },
	        error: function(error) {
				alert("Files have not been uploaded")
	            console.log("Error uploading files:", error);
	        }
	    });
    });

	
});