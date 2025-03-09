```
	function setValueByPath (object, path, value) {
		 const pathArray = path.split(".");
		 const attributeToSet = pathArray.pop();
		 let objectToModify = object;
		 for (const attr of pathArray) {
			if (typeof objectToModify[attr] !== 'object') {
				objectToModify[attr] = {};
				 }
			 objectToModify = objectToModify[attr];
			 }
		 objectToModify[attributeToSet] = value;
		 return object;
		 }
``````
	 setValueByPath({}, "__proto__.isAdmin", true)
	 setValueByPath({}, "constructor.prototype.isAdmin", true)
```


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-915 - Improperly Controlled Modification of Dynamically-Determined Object Attributes
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/915
//

//Source: CWE Mitre
//

//Extra context: 
// This function sets object attributes based on a dot-separated path.
// This function does not check if the attribute resolves to the object prototype. These codes can be used to add "isAdmin: true" to the object prototype.
// By using a denylist of dangerous attributes, this weakness can be eliminated.