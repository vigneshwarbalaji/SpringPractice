
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
</head>
<body>
	<h3>Welcome to ToDoApp</h3>
	
	Add Task:
	<input type="text" id="addTask" name="task" required="">
	
	Add Task Description:
	<input type="text" id="taskDescription" name="Description" required="">
	
	<input type="submit" id="Add" value="Add Task">
	
	<!-- <br><br>
	<input type="submit" id="View" value="View Task">
	 -->
	
	<br><br>
	<input type="submit" id="logOut" name="" value="Logout">
	
	<p id ="demo"></p>
	
	<Table border = '2' class = "tableContainer">
	<thead>
		<tr>
			<th>Task</th>
			<th>Task Description</th>
		</tr>
	</thead>
	
	</Table>
	
	<script src="js/AddTaskResponse.js"></script>
	
	
	<!-- <br><br>
	<form action="/logOut" method="GET">
    	<input type="submit" name="" value="Logout">
    </form>
	 -->
</body>
</html>