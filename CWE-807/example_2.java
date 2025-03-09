```
	Cookie[] cookies = request.getCookies();
	for (int i =0; i< cookies.length; i++) {
		Cookie c = cookies[i];
		if (c.getName().equals("authenticated") && Boolean.TRUE.equals(c.getValue())) {
			authenticated = true;
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
// In the following example, an authentication flag is read from a browser cookie, thus allowing for external control of user state data.