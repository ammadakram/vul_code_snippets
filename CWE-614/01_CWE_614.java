```
	Cookie c = new Cookie(ACCOUNT_ID, acctID);
	response.addCookie(c);
```


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-614 - Sensitive Data in URL
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/614
//

//Source: CWE Mitre
//

//Extra context: 
// The snippet of code below, taken from a servlet doPost() method, sets an accountID cookie (sensitive) without calling setSecure(true).