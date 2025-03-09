```
	my $username=param('username'); 
	my $password=param('password'); 
	if (IsValidUsername($username) == 1) 
	{ 
		if (IsValidPassword($username, $password) == 1) 
		{ 
			print "Login Successful"; 
		} 
		else 
		{ 
			print "Login Failed - incorrect password"; 
		} 
	} 
	else 
	{ 
		print "Login Failed - unknown username"; 
	} 
```


#Result: 
 # Vulnerable (01)
#
#
#
##Issue: CWE-200 - Exposure of Sensitive Information
#
#
#
#URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/200
#

#Source: CWE Mitre
#

#Extra context: 
# The following code checks validity of the supplied username and password and notifies the user of a successful or failed login.
# In the above code, there are different messages for when an incorrect username is supplied, versus when the username is correct but the password is wrong. This difference enables a potential attacker to understand the state of the login function, and could allow an attacker to discover a valid username by trying different values until the incorrect password message is returned. In essence, this makes it easier for an attacker to obtain half of the necessary authentication credentials.
# While this type of information may be helpful to a user, it is also useful to a potential attacker. In the above example, the message for both failed cases should be the same, such as: