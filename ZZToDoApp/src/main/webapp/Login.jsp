
<!DOCTYPE html>
<head>
    <title>Login</title>
      <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
    <link rel="stylesheet" type="text/css" href="css\style.css">
</head>

<body>
    <div class="box">
        <h2>Login</h2>
        <!-- <form> -->
            <div class="inputBox">
                <input type="text" id="logEmail" name="email" required="">
                <label>Email</label>
            </div>

            <div class="inputBox">
                <input type="password" id="logPass" name="password" required="">
                <label>Password</label>
            </div>
            
            <p id="name"></p>
            <div class="Login">
                <input type="submit" id="logSub" name="" value="Login" >
                <!-- onclick="loginFunc()" -->
            </div>
            
            <script src="js/UserResponse.js"></script>
        <!-- </form> -->
        <div class="NewUser">
        	<form action="/Signup">
        		<input type="submit" name="" id= "myButton" value="New User" >
        	</form>
            </div>
            
    </div>
</body>

</html>