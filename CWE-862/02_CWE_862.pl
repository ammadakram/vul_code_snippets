sub DisplayPrivateMessage {
	my($id) = @_;
	my $Message = LookupMessageObject($id);
	print "From: " . encodeHTML($Message->{from}) . "<br>\n";
	print "Subject: " . encodeHTML($Message->{subject}) . "\n";
	print "<hr>\n";
	print "Body: " . encodeHTML($Message->{body}) . "\n";
}
my $q = new CGI;
# For purposes of this example, assume that CWE-309 and* 



	*# CWE-523 do not apply.* 
if (! AuthenticateUser($q->param('username'), $q->param('password'))) {
	ExitError("invalid username or password");
}
my $id = $q->param('id');
DisplayPrivateMessage($id);


#Result: 
 # Vulnerable (01)
#
#
#
##Issue: CWE-862 - Missing Authorization
#
#
#
#URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/862
#

#Source: CWE Mitre
#

#Extra context: 
# The following program could be part of a bulletin board system that allows users to send private messages to each other. This program intends to authenticate the user before deciding whether a private message should be displayed. Assume that LookupMessageObject() ensures that the $id argument is numeric, constructs a filename based on that id, and reads the message details from that file. Also assume that the program stores all private messages for all users in the same directory.
# While the program properly exits if authentication fails, it does not ensure that the message is addressed to the user. As a result, an authenticated attacker could provide any arbitrary identifier and read private messages that were intended for other users.
# One way to avoid this problem would be to ensure that the "to" field in the message object matches the username of the authenticated user.