```
	int VerifyAdmin(String password) {
		if (!password.equals("Mew!")) {
			return(0)
		}
		//Diagnostic Mode
		return(1);
	}
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
// The following code is an example of an internal hard-coded password in the back-end:
// Every instance of this program can be placed into diagnostic mode with the same password. Even worse is the fact that if this program is distributed as a binary-only distribution, it is very difficult to change that password or disable this "functionality."