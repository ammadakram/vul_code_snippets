
def storePassword(userName,Password):
	hasher = hashlib.new('md5')
	hasher.update(Password)
	hashedPassword = hasher.digest()

# UpdateUserLogin returns True on success, False otherwise* 
	return updateUserLogin(userName,hashedPassword)


#Result: 
 # Vulnerable (01)
#
#
#
##Issue: CWE-759 - Use of a One-Way Hash without a Salt
#
#
#
#URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/759
#

#Source: CWE Mitre
#

#Extra context: 
# In this example, a new user provides a new username and password to create an account. The program hashes the new user's password then stores it in a database.
# While it is good to avoid storing a cleartext password, the program does not provide a salt to the hashing function, thus increasing the chances of an attacker being able to reverse the hash and discover the original password if the database is compromised.
# Fixing this is as simple as providing a salt to the hashing function on initialization:
# Note that regardless of the usage of a salt, the md5 hash is no longer considered secure, so this example still exhibits CWE-327.