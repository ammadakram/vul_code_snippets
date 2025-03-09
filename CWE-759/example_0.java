```
	String plainText = new String(plainTextIn);
	MessageDigest encer = MessageDigest.getInstance("SHA");
	encer.update(plainTextIn);
	byte[] digest = password.digest();
```
//Login if hash matches stored hash* 
	if (equal(digest,secret_password())) {
	```
		login_user();
	}
```


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-759 - Use of a One-Way Hash without a Salt
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/759
//

//Source: CWE Mitre
//

//Extra context: 
// In both of these examples, a user is logged in if their given password matches a stored password:
// This code relies exclusively on a password mechanism (CWE-309) using only one factor of authentication (CWE-308). If an attacker can steal or guess a user's password, they are given full access to their account. Note this code also uses SHA-1, which is a weak hash (CWE-328). It also does not use a salt (CWE-759).