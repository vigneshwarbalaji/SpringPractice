<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Adding Two Numbers</title>
</head>
<body>

Enter number a:
	<input type="text" id = "firstNo" name= "second"/>
Enter number b:
	<input type = "text" id = "secondNo" name = "first"/>
	<input type = "submit" value = "add" onclick="addNumbers()"/>
	
	<!--  <input type = "text" name="new" id = "result" />-->
	
	<br><br>
	Result:<br>
	<input type = "text" id = "res" name = "third"  />
	
	
	<!--  <output inputmode="text"  id = "result"></output>-->
</body>

<script src="js/ResponseServlet.js"></script>
</html>