
function runEmployeeQuery($dbName, $name){
	mysql_select_db($dbName,$globalDbHandle) or die("Could not open Database".$dbName);

//Use a prepared statement to avoid CWE-89* 
	$preparedStatement = $globalDbHandle->prepare('SELECT * FROM employees WHERE name = :name');
	$preparedStatement->execute(array(':name' => $name));
	return $preparedStatement->fetchAll();}

	*/.../* 

$employeeRecord = runEmployeeQuery('EmployeeDB',$_GET['EmployeeName']);


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-862 - Missing Authorization
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/862
//

//Source: CWE Mitre
//

//Extra context: 
// This function runs an arbitrary SQL query on a given database, returning the result of the query.
// While this code is careful to avoid SQL Injection, the function does not confirm the user sending the query is authorized to do so. An attacker may be able to obtain sensitive employee information from the database.