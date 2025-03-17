```
	Cookie[] cookies = request.getCookies();
	for (int i =0; i< cookies.length; i++) {
		Cookie c = cookies[i];
		if (c.getName().equals("role")) {
			userRole = c.getValue();
		}
	}
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
// The following code excerpt reads a value from a browser cookie to determine the role of the user.