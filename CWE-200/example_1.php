```
	try {
		openDbConnection();
	}
```
//print exception message that includes exception message and configuration file location* 
	catch (Exception $e) {
	```
		echo 'Caught exception: ', $e->getMessage(), '\n';
		echo 'Check credentials in config file at: ', $Mysql_config_location, '\n';
	}
```


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-200 - Exposure of Sensitive Information
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/200
//

//Source: CWE Mitre
//

//Extra context: 
// This code tries to open a database connection, and prints any exceptions that occur.
// If an exception occurs, the printed message exposes the location of the configuration file the script is using. An attacker can use this information to target the configuration file (perhaps exploiting a Path Traversal weakness). If the file can be read, the attacker could gain credentials for accessing the database. The attacker may also be able to replace the file with a malicious one, causing the application to use an arbitrary database.