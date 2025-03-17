
function persistLogin($username, $password){
	$data = array("username" => $username, "password"=> $password);
	setcookie ("userdata", $data);
}



//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-311 - Missing Encryption of Sensitive Data
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/311
//

//Source: CWE Mitre
//

//Extra context: 
// This code writes a user's login information to a cookie so the user does not have to login again later.
// The code stores the user's username and password in plaintext in a cookie on the user's machine. This exposes the user's login information if their computer is compromised by an attacker. Even if the user's machine is not compromised, this weakness combined with cross-site scripting (CWE-79) could allow an attacker to remotely copy the cookie.
// Also note this example code also exhibits Plaintext Storage in a Cookie (CWE-315).