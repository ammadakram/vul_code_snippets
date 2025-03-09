```
	locationClient = new LocationClient(this, this, this);
	locationClient.connect();
	currentUser.setLocation(locationClient.getLastLocation());
```
...* 
	
	catch (Exception e) {
	```
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Sorry, this application has experienced an error.");
		AlertDialog alert = builder.create();
		alert.show();
		Log.e("ExampleActivity", "Caught exception: " + e + " While on User:" + User.toString());
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
// This code stores location information about the current user:
// When the application encounters an exception it will write the user object to the log. Because the user object contains location information, the user's location is also written to the log.