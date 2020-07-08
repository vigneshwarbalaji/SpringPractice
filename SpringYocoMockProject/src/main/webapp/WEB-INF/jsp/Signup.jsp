<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <title>SignUp</title>
    <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
    <meta name="google-signin-client_id" content="1057523589135-9gb46eembt589ce228tfbetn8nhvmqkn.apps.googleusercontent.com">     
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <link rel="stylesheet" type="text/css" href="css\style.css">
</head>

<body>
    <div class="box">
        <h2>
            SignUp Form
        </h2>
        <!--  <form>-->
        
        <div align="center" onclick="ClickSignUp()" class="g-signin2" data-onsuccess="onSignUp"></div>
        <br><br>
        <script>
        
        var clicked=false;//Global Variable
        function ClickSignUp()
        {
            clicked=true;
        }
        
        
        
        function onSignUp(googleUser){
        	
        	if(clicked)
        	{
            var profile = googleUser.getBasicProfile();

        	var id_token = googleUser.getAuthResponse().id_token;
            	
        	//console.log("cat");
        	console.log("test 1"+id_token);
                $.ajax({
                	type: 'POST',
                    url: '/googleSignUp',
                    
                    
        				data:{
        					googleToken : id_token
        				},
                    success: function (data, status, xhr) {
                    	
                    	if(data.value == 'true')
                    	{
                    		window.location.href = '/Dashboard';
                    	}
                    	else
                    	{
                    		$('#name').html('<h6>'+'user already exist.please login'+'</h6>');
                    	}
                    	
                    },
                    dataType : 'json'
                });
        	}
        };
        </script>
        
         
            <div class="inputBox">
                <input type="text" name="username" id="signName" required="" oninvalid="username required">
                <label>username</label>
            </div>
            
            <div class="inputBox">
                <input type="email" name="email" id="signMail" required="" oninvalid="Email required">
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
                <input type="submit" name="" value="SignUp" id="signSub">
                <!-- onclick="createFunc()" -->
            </div>
       <!--  </form>--> 
        <script src="js/UserResponse.js"></script>
    </div>
</body>

</html>