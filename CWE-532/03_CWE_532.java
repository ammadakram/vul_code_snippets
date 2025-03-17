```
	public BankAccount getUserBankAccount(String username, String accountNumber) {
			BankAccount userAccount = null;
			String query = null;
			try {
				if (isAuthorizedUser(username)) {
					query = "SELECT * FROM accounts WHERE owner = "
					+ username + " AND accountID = " + accountNumber;
					DatabaseManager dbManager = new DatabaseManager();
					Connection conn = dbManager.getConnection();
					Statement stmt = conn.createStatement();
					ResultSet queryResult = stmt.executeQuery(query);
					userAccount = (BankAccount)queryResult.getObject(accountNumber);
				}
			} catch (SQLException ex) {
				String logMessage = "Unable to retrieve account information from database,\nquery: " + query;
				Logger.getLogger(BankManager.class.getName()).log(Level.SEVERE, logMessage, ex);
			}
			return userAccount;
	}
```


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-532 - Exposure of Sensitive Information in Logs
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/532
//

//Source: CWE Mitre
//

//Extra context: 
// In the example below, the method getUserBankAccount retrieves a bank account object from a database using the supplied username and account number to query the database. If an SQLException is raised when querying the database, an error message is created and output to a log file.
// The error message that is created includes information about the database query that may contain sensitive information about the database or query logic. In this case, the error message will expose the table name and column names used in the database. This data could be used to simplify other attacks, such as SQL injection (CWE-89) to directly access the database.