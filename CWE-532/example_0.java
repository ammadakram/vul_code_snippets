```
	logger.info("Username: " + usernme + ", CCN: " + ccn);
```


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-532 - Exposure of Sensitive Information in Logs
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/532
//

//Source: CWE Mitre
//

//Extra context: 
// In the following code snippet, a user's full name and credit card number are written to a log file.