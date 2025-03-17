```
	public boolean VerifyAdmin(String password) {
		if (password.equals("68af404b513073584c4b6f22b6c63e6b")) {
			System.out.println("Entering Diagnostic Mode...");
			return true;
		}
		System.out.println("Incorrect Password!");
		return false;
```


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-798 - Use of Hard-coded Credentials
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/798
//

//Source: CWE Mitre
//

//Extra context: 
// The following code examples attempt to verify a password using a hard-coded cryptographic key.
// The cryptographic key is within a hard-coded string value that is compared to the password. It is likely that an attacker will be able to read the key and compromise the system.