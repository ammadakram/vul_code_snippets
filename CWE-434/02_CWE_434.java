```
	public class FileUploadServlet extends HttpServlet {
			...
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					String contentType = request.getContentType();
					// the starting position of the boundary header
					int ind = contentType.indexOf("boundary=");
					String boundary = contentType.substring(ind+9);
					String pLine = new String();
					String uploadLocation = new String(UPLOAD_DIRECTORY_STRING); //Constant value
					// verify that content type is multipart form data
					if (contentType != null && contentType.indexOf("multipart/form-data") != -1) {
							// extract the filename from the Http header
							BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
							...
							pLine = br.readLine();
							String filename = pLine.substring(pLine.lastIndexOf("\\"), pLine.lastIndexOf("\""));
							...
							// output the file to the local upload directory
							try {
									BufferedWriter bw = new BufferedWriter(new FileWriter(uploadLocation+filename, true));
									for (String line; (line=br.readLine())!=null; ) {
										if (line.indexOf(boundary) == -1) {
											bw.write(line);
											bw.newLine();
											bw.flush();
										}
									} //end of for loop
									bw.close();
							} catch (IOException ex) {...}
							// output successful upload response HTML page
					}
					// output unsuccessful upload response HTML page
					else
					{...}
			}
				...
	}
```


//Result: 
 // Vulnerable (01)
//
//
//
////Issue: CWE-434 - Unrestricted File Upload
//
//
//
//URL: https://cwe-api.mitre.org/api/v1/cwe/weakness/434
//

//Source: CWE Mitre
//

//Extra context: 
// The following code demonstrates the unrestricted upload of a file with a Java servlet and a path traversal vulnerability. The action attribute of an HTML form is sending the upload file request to the Java servlet.
 When submitted the Java servlet's doPost method will receive the request, extract the name of the file from the Http request header, read the file contents from the request and output the file to the local upload directory.
// This code does not perform a check on the type of the file being uploaded (CWE-434). This could allow an attacker to upload any executable file or other file with malicious code.
// Additionally, the creation of the BufferedWriter object is subject to relative path traversal (CWE-23). Since the code does not check the filename that is provided in the header, an attacker can use "../" sequences to write to files outside of the intended directory. Depending on the executing environment, the attacker may be able to specify arbitrary files to write to, leading to a wide variety of consequences, from code execution, XSS (CWE-79), or system crash.