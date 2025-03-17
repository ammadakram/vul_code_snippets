```
```
# Java Web App ResourceBundle properties file* 
	...
	webapp.ldap.username=secretUsername
	webapp.ldap.password=secretPassword
	...


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-798 - Use of Hard-coded Credentials
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/798
//

//Source: CWE Mitre
//

//Extra context: 
// The following examples show a portion of properties and configuration files for Java and ASP.NET applications. The files include username and password information but they are stored in cleartext.
 This Java example shows a properties file with a cleartext username / password pair.
// The following example shows a portion of a configuration file for an ASP.Net application. This configuration file includes username and password information for a connection to a database but the pair is stored in cleartext.
// Username and password information should not be included in a configuration file or a properties file in cleartext as this will allow anyone who can read the file access to the resource. If possible, encrypt this information.