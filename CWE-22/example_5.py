```
	 import os
	 import sys
	 def main():
		 filename = sys.argv[1]
		 path = os.path.join(os.getcwd(), filename)
		 try:
			 with open(path, 'r') as f:
				 file_data = f.read()
		 except FileNotFoundError as e:
			 print("Error - file not found")
	 main() 
```


#Result: 
 # Vulnerable (01)
#
#
#
##Issue: CWE-22 - Path Traversal
#
#
#
#URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/22
#

#Source: CWE Mitre
#

#Extra context: 
# This script intends to read a user-supplied file from the current directory. The user inputs the relative path to the file and the script uses Python's os.path.join() function to combine the path to the current working directory with the provided path to the specified file. This results in an absolute path to the desired file. If the file does not exist when the script attempts to read it, an error is printed to the user.
# However, if the user supplies an absolute path, the os.path.join() function will discard the path to the current working directory and use only the absolute path provided. For example, if the current working directory is /home/user/documents, but the user inputs /etc/passwd, os.path.join() will use only /etc/passwd, as it is considered an absolute path. In the above scenario, this would cause the script to access and read the /etc/passwd file.
# The constructed path string uses os.sep to add the appropriate separation character for the given operating system (e.g. '\' or '/') and the call to os.path.normpath() removes any additional slashes that may have been entered - this may occur particularly when using a Windows path. The path is checked against an expected directory (/home/cwe/documents); otherwise, an attacker could provide relative path sequences like ".." to cause normpath() to generate paths that are outside the intended directory (CWE-23). By putting the pieces of the path string together in this fashion, the script avoids a call to os.path.join() and any potential issues that might arise if an absolute path is entered. With this version of the script, if the current working directory is /home/cwe/documents, and the user inputs /etc/passwd, the resulting path will be /home/cwe/documents/etc/passwd. The user is therefore contained within the current working directory as intended.