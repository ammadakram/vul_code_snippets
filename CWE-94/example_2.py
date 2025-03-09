```
	 def main():
		 sum = 0
		 numbers = eval(input("Enter a space-separated list of numbers: "))
		 for num in numbers:
			 sum = sum + num
		 print(f"Sum of {numbers} = {sum}") 
	 main() 
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
# This simple script asks a user to supply a list of numbers as input and adds them together.
# The eval() function can take the user-supplied list and convert it into a Python list object, therefore allowing the programmer to use list comprehension methods to work with the data. However, if code is supplied to the eval() function, it will execute that code. For example, a malicious user could supply the following string:
# ```
	__import__('subprocess').getoutput('rm -r *')
```
# This would delete all the files in the current directory. For this reason, it is not recommended to use eval() with untrusted input.
# A way to accomplish this without the use of eval() is to apply an integer conversion on the input within a try/except block. If the user-supplied input is not numeric, this will raise a ValueError. By avoiding eval(), there is no opportunity for the input string to be executed as code.
# An alternative, commonly-cited mitigation for this kind of weakness is to use the ast.literal_eval() function, since it is intentionally designed to avoid executing code. However, an adversary could still cause excessive memory or stack consumption via deeply nested structures [REF-1372], so the python documentation discourages use of ast.literal_eval() on untrusted data [REF-1373].