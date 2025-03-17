```
	class Worker implements Executor {
			...
			public void execute(Runnable r) {
					try {
						...
					}
					catch (InterruptedException ie) {
```
// postpone response* 
							Thread.currentThread().interrupt();}}
			
			public Worker(Channel ch, int nworkers) {
			```
				...
			}
			protected void activate() {
					Runnable loop = new Runnable() {
							public void run() {
									try {
										for (;;) {
											Runnable r = ...;
											r.run();
										}
									}
									catch (InterruptedException ie) {
										...
									}
							}
					};
					new Thread(loop).start();
			}
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
// The following example demonstrates the weakness.
// There are no limits to runnables. Potentially an attacker could cause resource problems very quickly.