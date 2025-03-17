
my $dataPath = "/users/cwe/profiles";
my $username = param("user");
my $profilePath = $dataPath . "/" . $username;
open(my $fh, "<", $profilePath) || ExitError("profile read error: $profilePath");
print "<ul>\n";
while (<$fh>) {
	print "<li>$_</li>\n";
}
print "</ul>\n";



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
# The following code could be for a social networking application in which each user's profile information is stored in a separate file. All files are stored in a single directory.
# While the programmer intends to access files such as "/users/cwe/profiles/alice" or "/users/cwe/profiles/bob", there is no verification of the incoming user parameter. An attacker could provide a string such as:
# ```
	../../../etc/passwd
```
# The program would generate a profile pathname like this:
# When the file is opened, the operating system resolves the "../" during path canonicalization and actually accesses this file:
# As a result, the attacker could read the entire text of the password file.
# Notice how this code also contains an error message information leak (CWE-209) if the user parameter does not produce a file that exists: the full pathname is provided. Because of the lack of output encoding of the file that is retrieved, there might also be a cross-site scripting problem (CWE-79) if profile contains any HTML, but other code would need to be examined.