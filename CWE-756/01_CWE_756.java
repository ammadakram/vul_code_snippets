```
	Public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			...
		} catch (ApplicationSpecificException ase) {
			logger.error("Caught: " + ase.toString());
		}
	}
```


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-756 - Missing Custom Error Page
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/756
//

//Source: CWE Mitre
//

//Extra context: 
// In the snippet below, an unchecked runtime exception thrown from within the try block may cause the container to display its default error page (which may contain a full stack trace, among other things).