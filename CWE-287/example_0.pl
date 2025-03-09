```
	my $q = new CGI;
	if ($q->cookie('loggedin') ne "true") {
			if (! AuthenticateUser($q->param('username'), $q->param('password'))) {
				ExitError("Error: you need to log in first");
			}
			else {
					# Set loggedin and user cookies.
					$q->cookie(
						-name => 'loggedin',
						-value => 'true'
						);
					$q->cookie(
						-name => 'user',
						-value => $q->param('username')
						);
			}
	}
	if ($q->cookie('user') eq "Administrator") {
		DoAdministratorTasks();
	}
```


#Result: 
 # Vulnerable (01)
#
#
#
##Issue: CWE-287 - Improper Authentication
#
#
#
#URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/287
#

#Source: CWE Mitre
#

#Extra context: 
# The following code intends to ensure that the user is already logged in. If not, the code performs authentication with the user-provided username and password. If successful, it sets the loggedin and user cookies to "remember" that the user has already logged in. Finally, the code performs administrator tasks if the logged-in user has the "Administrator" username, as recorded in the user cookie.
# Unfortunately, this code can be bypassed. The attacker can set the cookies independently so that the code does not check the username and password. The attacker could do this with an HTTP request containing headers such as:
# ```
	GET /cgi-bin/vulnerable.cgi HTTP/1.1
	Cookie: user=Administrator
	Cookie: loggedin=true
	[body of request]
```
# By setting the loggedin cookie to "true", the attacker bypasses the entire authentication check. By using the "Administrator" value in the user cookie, the attacker also gains privileges to administer the software.