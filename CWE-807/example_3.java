```
	String ip = request.getRemoteAddr();
	InetAddress addr = InetAddress.getByName(ip);
	if (addr.getCanonicalHostName().endsWith("trustme.com")) {
		trusted = true;
	}
```


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-807 - Reliance on Untrusted Inputs for Security Decisions
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/807
//

//Source: CWE Mitre
//

//Extra context: 
// The following code samples use a DNS lookup in order to decide whether or not an inbound request is from a trusted host. If an attacker can poison the DNS cache, they can gain trusted status.
// IP addresses are more reliable than DNS names, but they can also be spoofed. Attackers can easily forge the source IP address of the packets they send, but response packets will return to the forged IP address. To see the response packets, the attacker has to sniff the traffic between the victim machine and the forged IP address. In order to accomplish the required sniffing, attackers typically attempt to locate themselves on the same subnet as the victim machine. Attackers may be able to circumvent this requirement by using source routing, but source routing is disabled across much of the Internet today. In summary, IP address verification can be a useful part of an authentication scheme, but it should not be the single factor required for authentication.