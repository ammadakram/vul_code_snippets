```
	$auth = $_COOKIES['authenticated'];
	if (! $auth) {
		if (AuthenticateUser($_POST['user'], $_POST['password']) == "success") {
			// save the cookie to send out in future responses
			setcookie("authenticated", "1", time()+60*60*2);
		}
		else {
			ShowLoginScreen();
			die("\n");
		}
	}
	DisplayMedicalHistory($_POST['patient_ID']);
```


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-807 - Reliance on Untrusted Inputs for Security Decisions
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/807
//

//Source: CWE Mitre
//

//Extra context: 
// The following code could be for a medical records application. It performs authentication by checking if a cookie has been set.
// The programmer expects that the AuthenticateUser() check will always be applied, and the "authenticated" cookie will only be set when authentication succeeds. The programmer even diligently specifies a 2-hour expiration for the cookie.
// However, the attacker can set the "authenticated" cookie to a non-zero value such as 1. As a result, the $auth variable is 1, and the AuthenticateUser() check is not even performed. The attacker has bypassed the authentication.