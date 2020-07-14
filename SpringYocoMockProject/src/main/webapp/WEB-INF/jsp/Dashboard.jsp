<%@page import="com.googlecode.objectify.impl.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>

<head>
    <title>
        Time Tracker
    </title>
    <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests"> 
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" type="text/css" href="css\newstyle.css">
    <link rel="stylesheet" type="text/css" href="css\Adjustment.css">
</head>

<body>
<%String name = session.getAttribute("name").toString();
  String email = session.getAttribute("email").toString();

  
 // UserService dao = new UserServiceImpl();
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
        <input type="button" id="clockInInput" name="" value="Clock-In">
        <!-- onclick="clockInFunc()" -->
    </div>
    
    
    <div class="bluey" id="clockOut">
        <input type="button" id="clockOutInput" name="" value="Clock-Out">
        <!-- onclick="clockOutFunc()" -->
    </div>

    <div class="bluey" id="Timezone" align="center">
        <!-- <input type="button" name="" value="timezone"> -->
        <select name=timeZone id=zone>
        
        <option id="drop" value="zone">Zones</option>
        </select>
        
        <input type="submit" name="" id="confirm">
    </div>

    <!-- <div class="heading1">
        <strong>Entries</strong>
    </div>-->
  
  <!--  <div class = "adjustment" id="addAdjustments" align="right">
    <span class="material-icons">
        add_circle_outline
    </span>
    </div> -->
    
    <div id="myModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <span class="close">&times;</span>
    <!--<p>Some text in the Modal..</p>-->
    <h1>Adjustments</h1><br>
    
    <p id = 'adjustAlert'></p><br><br>
    
    <label>Clock In:</label>
    <input type="date" id="startDate" value = "2020-01-01" >
    <input type="time" id="inTime" ><br><br>
    <label>Clock Out:</label>
    <input type="date" id="stopDate" value = "2020-01-01" >
    <input type="time" id="outTime"><br><br>
    
    <label>Project:</label><br>
    <input type="text" id="upPro" value=""><br><br>
    <label>Description:</label><br>
    <input type="text" id="upDes" value=""><br><br>
    
    <div class="bluey" class="adjust">
    <input type="submit" id="upSave" value="Save"><br>
    </div>
  </div>
    </div>
    
    <br><br>
    
	<!--<input type = "button" id ="new" name="projectbutton" onclick="clockInFunc()" value = "submit"><br><br>
	-->
<script src="js/ClockResponse.js"></script>




    <Table class="tabcont">
    <thead>
     	<tr id = "rows">
            <th id="th1">Add Task Description</th>
            <th id="th2">Project</th>
            <th id="th3">clock-In</th>
            <th id="th4">clock-out</th>
            <th id="th5">Total Hours
            
            <!-- 
              <div class = "adjustment" id="addAdjustments" align="right">
    			<span class="material-icons">
        			add_circle_outline
    			</span>
    		</div>
    		<div class = "adjustment" id="addAdjustments" align="right">
    			<span class="material-icons">
        			add_circle_outline
    			</span>
    		</div> -->
            </th>
        </tr>
        
     </thead>
        <!--  <tr id = "rows">
           <td id = "one"></td>
            <td id = two></td>
            <td id = three></td>
            <td id = "four"></td>
            <td id = "five">
                <span class="material-icons" id="">create</span>
            </td>
        </tr>-->
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
    <form action="/logOut" method="get">
    	<input type="submit" name="" value="Logout">
    </form>
    </div>
    
    <!--  <a href="#" onclick="signOut();">Sign out</a>
	<script>
  	function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
      
      window.location.href = '/';
    });
  }
  	
    function onLoad() {
        gapi.load('auth2', function() {
          gapi.auth2.init();
        });
      }

</script>-->
    
    <div class="bluey" id="task" align="center">
        <input type="button" id="burst" name="" value="burst">
    </div>
</body>

</html>