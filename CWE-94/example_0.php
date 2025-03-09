```
	$MessageFile = "messages.out";
	if ($_GET["action"] == "NewMessage") {
		$name = $_GET["name"];
		$message = $_GET["message"];
		$handle = fopen($MessageFile, "a+");
		fwrite($handle, "<b>$name</b> says '$message'<hr>\n");
		fclose($handle);
		echo "Message Saved!<p>\n";
	}
	else if ($_GET["action"] == "ViewMessages") {
		include($MessageFile);
	}
```


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-94 - Code Injection
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/94
//

//Source: CWE Mitre
//

//Extra context: 
// This example attempts to write user messages to a message file and allow users to view them.
// While the programmer intends for the MessageFile to only include data, an attacker can provide a message such as:
// ```
	name=h4x0r
	message=%3C?php%20system(%22/bin/ls%20-l%22);?%3E
```
// which will decode to the following:
// ```
	<?php system("/bin/ls -l");?>
```
// The programmer thought they were just including the contents of a regular data file, but PHP parsed it and executed the code. Now, this code is executed any time people view messages.
// Notice that XSS (CWE-79) is also possible in this situation.