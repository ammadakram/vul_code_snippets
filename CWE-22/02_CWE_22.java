```
	String filename = System.getProperty("com.domain.application.dictionaryFile");
	File dictionaryFile = new File(filename);
```


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-22 - Path Traversal
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/22
//

//Source: CWE Mitre
//

//Extra context: 
// In the example below, the path to a dictionary file is read from a system property and used to initialize a File object.
// However, the path is not validated or modified to prevent it from containing relative or absolute path sequences before creating the File object. This allows anyone who can control the system property to determine what file is used. Ideally, the path should be resolved relative to some kind of application or user home directory.