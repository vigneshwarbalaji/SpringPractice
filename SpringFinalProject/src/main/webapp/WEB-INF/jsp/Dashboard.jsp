<%@page import="com.googlecode.objectify.impl.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>

<head>
    <title>
        Time Tracker
    </title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" type="text/css" href="css\newstyle.css">
</head>



<body>
<%String name = session.getAttribute("name").toString();
  String email = session.getAttribute("email").toString();
%>
    <header>
        <div class="logo">
            <strong><%out.print(name); %></strong>
        </div>
    </header>
    
    <div class="heading1">
        <strong>Entries</strong>
    </div>
    <br><br>
	<p id = "warn"><br>
    Project:
    <input type = "text" id ="project" name="projectDetail"><br><br>
    Description:
	<input type = "text" id ="descript" name="taskDescript"><br><br>

    <!--<div class="clockies">-->
    <div class="bluey" id="clockIn">
        <input type="button" id="clockInInput" name="" value="Clock-In" onclick="clockInFunc()">
    </div>
    
    
    <div class="bluey" id="clockOut">
        <input type="button" id="clockOutInput" name="" value="Clock-Out" onclick="clockOutFunc()">
    </div>

    <div class="bluey" id="Timezone" align="center">
        <input type="button" name="" value="timezone">
    </div>

    <!-- <div class="heading1">
        <strong>Entries</strong>
    </div>-->

    <span class="material-icons" id="i0">
        add_circle_outline
    </span>
    
    <br><br>
    
	<!--<input type = "button" id ="new" name="projectbutton" onclick="clockInFunc()" value = "submit"><br><br>
	-->
<script src="js/ClockResponse.js"></script>
    <Table class="tabcont">
        <tr>
            <th id="th1">Add Task Description</th>
            <th id="th2">Project</th>
            <th id="th3">clock-In</th>
            <th id="th4">clock-out</th>
            <th id="th5">Total Hours</th>
        </tr>
        <tr>
            <td id = "one"></td>
            <td id = two></td>
            <td id = three></td>
            <td id = "four"></td>
            <td id = "five">
                <span class="material-icons" id="">create</span>
            </td>
        </tr>
       <!-- <tr>
            <td>Log In Feature</td>
            <td>Mock Design</td>
            <td>9:00 AM</td>
            <td>4:00 pm</td>
            <td>7h
                <span class="material-icons" id="">create</span>
            </td>
        </tr-->

    </Table>
    </div>
    
    

    <div class="bluey" class="Logout" align="center">
        <input type="button" name="" value="Logout">
    </div>
</body>

</html>