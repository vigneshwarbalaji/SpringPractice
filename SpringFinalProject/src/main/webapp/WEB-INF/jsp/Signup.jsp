<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <title>SignUp</title>
    <link rel="stylesheet" type="text/css" href="css\style.css">
</head>

<body>
    <div class="box">
        <h2>
            SignUp Form
        </h2>
        <!--  <form>--> 
            <div class="inputBox">
                <input type="text" name="username" id="signName" required="" oninvalid="username required">
                <label>username</label>
            </div>
            
            <div class="inputBox">
                <input type="text" name="email" id="signMail" required="" oninvalid="Email required">
                <label>Email</label>
            </div>

            <div class="inputBox">
                <input type="password" name="password" id="signPass" required="" oninvalid="password required">
                <label>password</label>
            </div>

            <div class="inputBox">
                <input type="password" name="retypepassword" id="signRePass" required="" oninvalid="password required">
                <label>Re-type password</label>
            </div>
			<p id="name"></p>
            <div class="SignUp">
                <input type="submit" name="" value="SignUp" id="signSub" onclick="createFunc()" >
            </div>
       <!--  </form>--> 
        <script src="js/UserResponse.js"></script>
    </div>
</body>

</html>