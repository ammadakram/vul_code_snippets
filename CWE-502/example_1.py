```
	try {
			class ExampleProtocol(protocol.Protocol):
			def dataReceived(self, data):
			# Code that would be here would parse the incoming data
			# After receiving headers, call confirmAuth() to authenticate
			def confirmAuth(self, headers):
			try:
			token = cPickle.loads(base64.b64decode(headers['AuthToken']))
			if not check_hmac(token['signature'], token['data'], getSecretKey()):
			raise AuthFail
			self.secure_data = token['data']
			except:
			raise AuthFail
	}
```


#Result: 
 # Vulnerable (01)
#
#
#
##Issue: CWE-502 - Deserialization of Untrusted Data
#
#
#
#URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/502
#

#Source: CWE Mitre
#

#Extra context: 
# In Python, the Pickle library handles the serialization and deserialization processes. In this example derived from [REF-467], the code receives and parses data, and afterwards tries to authenticate a user based on validating a token.
# Unfortunately, the code does not verify that the incoming data is legitimate. An attacker can construct a illegitimate, serialized object "AuthToken" that instantiates one of Python's subprocesses to execute arbitrary commands. For instance,the attacker could construct a pickle that leverages Python's subprocess module, which spawns new processes and includes a number of arguments for various uses. Since Pickle allows objects to define the process for how they should be unpickled, the attacker can direct the unpickle process to call Popen in the subprocess module and execute /bin/sh.