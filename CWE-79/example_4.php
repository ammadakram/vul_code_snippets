```
	$name = $_COOKIE["myname"];
	$announceStr = "$name just logged in.";
```
//save HTML-formatted message to file; implementation details are irrelevant for this example.* 
	 saveMessage($announceStr);


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-79 - Cross-Site Scripting (XSS)
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/79
//

//Source: CWE Mitre
//

//Extra context: 
// The following code is a simplistic message board that saves messages in HTML format and appends them to a file. When a new user arrives in the room, it makes an announcement:
// An attacker may be able to perform an HTML injection (Type 2 XSS) attack by setting a cookie to a value like:
// ```
	<script>document.alert('Hacked');</script>
```
// The raw contents of the message file would look like:
// For each person who visits the message page, their browser would execute the script, generating a pop-up window that says "Hacked". More malicious attacks are possible; see the rest of this entry.