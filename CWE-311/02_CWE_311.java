```
	try {
		URL u = new URL("http://www.secret.example.org/");
		HttpURLConnection hu = (HttpURLConnection) u.openConnection();
		hu.setRequestMethod("PUT");
		hu.connect();
		OutputStream os = hu.getOutputStream();
		hu.disconnect();
	}
	catch (IOException e) {
```
//...* 
			}


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-311 - Missing Encryption of Sensitive Data
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/311
//

//Source: CWE Mitre
//

//Extra context: 
// The following code attempts to establish a connection to a site to communicate sensitive information.
// Though a connection is successfully made, the connection is unencrypted and it is possible that all sensitive data sent to or received from the server will be read by unintended actors.