
my $Username = GetUntrustedInput();
$Username =~ s/\.\.\///;
my $filename = "/home/user/" . $Username;
ReadAndSendFile($filename);



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
# The following code takes untrusted input and uses a regular expression to filter "../" from the input. It then appends this result to the /home/user/ directory and attempts to read the file in the final resulting path.
# Since the regular expression does not have the /g global match modifier, it only removes the first instance of "../" it comes across. So an input value such as:
# ```
	../../../etc/passwd
```
# will have the first "../" stripped, resulting in:
# This value is then concatenated with the /home/user/ directory:
# which causes the /etc/passwd file to be retrieved once the operating system has resolved the ../ sequences in the pathname. This leads to relative path traversal (CWE-23).