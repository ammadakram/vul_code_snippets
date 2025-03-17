
// Define the target location where the picture being* 


	*// uploaded is going to be saved.* 
$target = "pictures/" . basename($_FILES['uploadedfile']['name']);


	*// Move the uploaded file to the new location.* 
if(move_uploaded_file($_FILES['uploadedfile']['tmp_name'], $target))
{

	echo "The picture has been successfully uploaded.";
}
else
{
	echo "There was an error uploading the picture, please try again.";
}


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-434 - Unrestricted File Upload
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/434
//

//Source: CWE Mitre
//

//Extra context: 
// The following code intends to allow a user to upload a picture to the web server. The HTML code that drives the form on the user end has an input field of type "file".
 Once submitted, the form above sends the file to upload_picture.php on the web server. PHP stores the file in a temporary location until it is retrieved (or discarded) by the server side code. In this example, the file is moved to a more permanent pictures/ directory.
// The problem with the above code is that there is no check regarding type of file being uploaded. Assuming that pictures/ is available in the web document root, an attacker could upload a file with the name:
// ```
	malicious.php
```
// Since this filename ends in ".php" it can be executed by the web server. In the contents of this uploaded file, the attacker could use:
// ```
	<?php
		system($_GET['cmd']);
	?>
```
// Once this file has been installed, the attacker can enter arbitrary commands to execute using a URL such as:
// ```
	http://server.example.com/upload_dir/malicious.php?cmd=ls%20-l
```
// which runs the "ls -l" command - or any other type of command that the attacker wants to specify.