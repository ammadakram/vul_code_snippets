```
	use CGI qw(:standard);
	sub config_file_add_key {
			my ($fname, $key, $arg) = @_;
```
# code to add a field/key to a file goes here* 
			}
	
	sub config_file_set_key {
	```
			my ($fname, $key, $arg) = @_;
```
# code to set key to a particular file goes here* 
			}
	
	sub config_file_delete_key {
	```
			my ($fname, $key, $arg) = @_;
```
# code to delete key from a particular file goes here* 
			}
	
	sub handleConfigAction {
	```
			my ($fname, $action) = @_;
			my $key = param('key');
			my $val = param('val');
```
# this is super-efficient code, especially if you have to invoke* 
			
			
			
			 *# any one of dozens of different functions!* 
			
			my $code = "config_file_$action_key(\$fname, \$key, \$val);";
			eval($code);}
	
	$configfile = "/home/cwe/config.txt";
	print header;
	if (defined(param('action'))) {
	```
		handleConfigAction($configfile, param('action'));
	}
	else {
		print "No action specified!\n";
	}
```


#Result: 
 # Vulnerable (01)
#
#
#
##Issue: CWE-94 - Code Injection
#
#
#
#URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/94
#

#Source: CWE Mitre
#

#Extra context: 
# edit-config.pl: This CGI script is used to modify settings in a configuration file.
# The script intends to take the 'action' parameter and invoke one of a variety of functions based on the value of that parameter - config_file_add_key(), config_file_set_key(), or config_file_delete_key(). It could set up a conditional to invoke each function separately, but eval() is a powerful way of doing the same thing in fewer lines of code, especially when a large number of functions or variables are involved. Unfortunately, in this case, the attacker can provide other values in the action parameter, such as:
# ```
	add_key(",","); system("/bin/ls");
```
# This would produce the following string in handleConfigAction():
# Any arbitrary Perl code could be added after the attacker has "closed off" the construction of the original function call, in order to prevent parsing errors from causing the malicious eval() to fail before the attacker's payload is activated. This particular manipulation would fail after the system() call, because the "_key(\$fname, \$key, \$val)" portion of the string would cause an error, but this is irrelevant to the attack because the payload has already been activated.