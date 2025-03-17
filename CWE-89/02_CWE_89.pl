
$userKey = getUserID();
$name = getUserInput();

# ensure only letters, hyphens and apostrophe are allowed* 
$name = allowList($name, "^a-zA-z'-$");
$query = "INSERT INTO last_names VALUES('$userKey', '$name')";


#Result: 
 # Vulnerable (01)
#
#
#
##Issue: CWE-89 - SQL Injection
#
#
#
#URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/89
#

#Source: CWE Mitre
#

#Extra context: 
# This example attempts to take a last name provided by a user and enter it into a database.
# While the programmer applies an allowlist to the user input, it has shortcomings. First of all, the user is still allowed to provide hyphens, which are used as comment structures in SQL. If a user specifies "--" then the remainder of the statement will be treated as a comment, which may bypass security logic. Furthermore, the allowlist permits the apostrophe, which is also a data / command separator in SQL. If a user supplies a name with an apostrophe, they may be able to alter the structure of the whole statement and even change control flow of the program, possibly accessing or modifying confidential information. In this situation, both the hyphen and apostrophe are legitimate characters for a last name and permitting them is required. Instead, a programmer may want to use a prepared statement or apply an encoding routine to the input to prevent any data / directive misinterpretations.