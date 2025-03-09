```
	$redirect_url = $_GET['url'];
	header("Location: " . $redirect_url);
```


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-601 - Open Redirect
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/601
//

//Source: CWE Mitre
//

//Extra context: 
// The following code obtains a URL from the query string and then redirects the user to that URL.
// The problem with the above code is that an attacker could use this page as part of a phishing scam by redirecting users to a malicious site. For example, assume the above code is in the file example.php. An attacker could supply a user with the following link:
// ```
	http://example.com/example.php?url=http://malicious.example.com
```
// The user sees the link pointing to the original trusted site (example.com) and does not realize the redirection that could take place.