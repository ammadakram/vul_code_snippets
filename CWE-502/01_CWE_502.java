```
	try {
		File file = new File("object.obj");
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		javax.swing.JButton button = (javax.swing.JButton) in.readObject();
		in.close();
	}
```


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-502 - Deserialization of Untrusted Data
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/502
//

//Source: CWE Mitre
//

//Extra context: 
// This code snippet deserializes an object from a file and uses it as a UI button:
// This code does not attempt to verify the source or contents of the file before deserializing it. An attacker may be able to replace the intended file with a file that contains arbitrary malicious code which will be executed when the button is pressed.
// To mitigate this, explicitly define final readObject() to prevent deserialization. An example of this is: