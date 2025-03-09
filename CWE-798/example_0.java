```
	...
	DriverManager.getConnection(url, "scott", "tiger");
	...
```


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
// The following code uses a hard-coded password to connect to a database:
// This is an example of an external hard-coded password on the client-side of a connection. This code will run successfully, but anyone who has access to it will have access to the password. Once the program has shipped, there is no going back from the database user "scott" with a password of "tiger" unless the program is patched. A devious employee with access to this information can use it to break into the system. Even worse, if attackers have access to the bytecode for application, they can use the javap -c command to access the disassembled code, which will contain the values of the passwords used. The result of this operation might look something like the following for the example above:
// ```
	javap -c ConnMngr.class
		22: ldc #36; //String jdbc:mysql://ixne.com/rxsql
		24: ldc #38; //String scott
		26: ldc #17; //String tiger
```