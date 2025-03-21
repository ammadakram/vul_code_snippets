```
	public class RedirectServlet extends HttpServlet {
			protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String query = request.getQueryString();
				if (query.contains("url")) {
					String url = request.getParameter("url");
					response.sendRedirect(url);
				}
			}
	}
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
// The following code is a Java servlet that will receive a GET request with a url parameter in the request to redirect the browser to the address specified in the url parameter. The servlet will retrieve the url parameter value from the request and send a response to redirect the browser to the url address.
// The problem with this Java servlet code is that an attacker could use the RedirectServlet as part of an e-mail phishing scam to redirect users to a malicious site. An attacker could send an HTML formatted e-mail directing the user to log into their account by including in the e-mail the following link:
// ```
	<a href="http://bank.example.com/redirect?url=http://attacker.example.net">Click here to log in</a>
```
// The user may assume that the link is safe since the URL starts with their trusted bank, bank.example.com. However, the user will then be redirected to the attacker's web site (attacker.example.net) which the attacker may have made to appear very similar to bank.example.com. The user may then unwittingly enter credentials into the attacker's web page and compromise their bank account. A Java servlet should never redirect a user to a URL without verifying that the redirect address is a trusted site.