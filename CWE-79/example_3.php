```
	$username = mysql_real_escape_string($username);
	$fullName = mysql_real_escape_string($fullName);
	$query = sprintf('Insert Into users (username,password) Values ("%s","%s","%s")', $username, crypt($password),$fullName) ;
	mysql_query($query);
	/.../
``````
	$query = 'Select * From users Where loggedIn=true';
	$results = mysql_query($query);
	if (!$results) {
		exit;
	}
```
//Print list of users to page* 
	echo '<div id="userlist">Currently Active Users:';
	while ($row = mysql_fetch_assoc($results)) {
	```
		echo '<div class="userNames">'.$row['fullname'].'</div>';
	}
	echo '</div>';
```


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
// The following code consists of two separate pages in a web application, one devoted to creating user accounts and another devoted to listing active users currently logged in. It also displays a Stored XSS (Type 2) scenario.
 CreateUser.php
// The code is careful to avoid a SQL injection attack (CWE-89) but does not stop valid HTML from being stored in the database. This can be exploited later when ListUsers.php retrieves the information:
// ListUsers.php
// The attacker can set their name to be arbitrary HTML, which will then be displayed to all visitors of the Active Users page. This HTML can, for example, be a password stealing Login message.