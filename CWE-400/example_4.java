```
	public void acceptConnections() {
			try {
				ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
				int counter = 0;
				boolean hasConnections = true;
				while (hasConnections) {
					Socket client = serverSocket.accept();
					Thread t = new Thread(new ClientSocketThread(client));
					t.setName(client.getInetAddress().getHostName() + ":" + counter++);
					t.start();
				}
				serverSocket.close();
			} catch (IOException ex) {...}
	}
```


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-400 - Uncontrolled Resource Consumption (DoS)
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/400
//

//Source: CWE Mitre
//

//Extra context: 
// In the following example, a server object creates a server socket and accepts client connections to the socket. For every client connection to the socket a separate thread object is generated using the ClientSocketThread class that handles request made by the client through the socket.
// In this example there is no limit to the number of client connections and client threads that are created. Allowing an unlimited number of client connections and threads could potentially overwhelm the system and system resources.
// The server should limit the number of client connections and the client threads that are created. This can be easily done by creating a thread pool object that limits the number of threads that are generated.