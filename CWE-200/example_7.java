```
	locationClient = new LocationClient(this, this, this);
	locationClient.connect();
	Location userCurrLocation;
	userCurrLocation = locationClient.getLastLocation();
	deriveStateFromCoords(userCurrLocation);
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
// This code uses location to determine the user's current US State location.
 First the application must declare that it requires the ACCESS_FINE_LOCATION permission in the application's manifest.xml:
 During execution, a call to getLastLocation() will return a location based on the application's location permissions. In this case the application has permission for the most accurate location possible:
// While the application needs this information, it does not need to use the ACCESS_FINE_LOCATION permission, as the ACCESS_COARSE_LOCATION permission will be sufficient to identify which US state the user is in.