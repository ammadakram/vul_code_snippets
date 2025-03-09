```
	$id = $_COOKIE["mid"];
	mysql_query("SELECT MessageID, Subject FROM messages WHERE MessageID = '$id'");
```


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-89 - SQL Injection
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/89
//

//Source: CWE Mitre
//

//Extra context: 
// This code intends to print a message summary given the message ID.
// The programmer may have skipped any input validation on $id under the assumption that attackers cannot modify the cookie. However, this is easy to do with custom client code or even in the web browser.
// While $id is wrapped in single quotes in the call to mysql_query(), an attacker could simply change the incoming mid cookie to:
// ```
	1432' or '1' = '1
```
// This would produce the resulting query:
// Not only will this retrieve message number 1432, it will retrieve all other messages.
// In this case, the programmer could apply a simple modification to the code to eliminate the SQL injection:
// However, if this code is intended to support multiple users with different message boxes, the code might also need an access control check (CWE-285) to ensure that the application user has the permission to see that message.