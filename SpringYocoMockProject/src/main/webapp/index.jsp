<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<head>
    <title>Login</title>
      <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
          <meta name="google-signin-client_id" content="1057523589135-9gb46eembt589ce228tfbetn8nhvmqkn.apps.googleusercontent.com">     
      <script src="https://apis.google.com/js/platform.js" async defer></script>
    <link rel="stylesheet" type="text/css" href="css\style.css">
</head>

<body>
    <div class="box">
        <h2>Login</h2>
        <!-- <form> -->
        
        <div align="center" onclick="ClickLogin()" class="g-signin2" data-onsuccess="onSignIn"></div>
        
        
        <script>
        
        var clicked=false;//Global Variable
        function ClickLogin()
        {
            clicked=true;
        }
        
        function onSignIn(googleUser){
        
        	if(clicked)
        	{
            var profile = googleUser.getBasicProfile();

        	var id_token = googleUser.getAuthResponse().id_token;
            	
        	//console.log("cat");
        	console.log("test 1"+id_token);
                $.ajax({
                	type: 'POST',
                    url: '/googleSignIn',
                    
                    
        				data:{
        					googleToken : id_token
        				},
                    success: function (data, status, xhr) {
                    	
//                    	if(data.value == 'true')
//            			{
//                    		window.location.href = '/Dashboard';
//            			}
//            		else
//            			{
//            			$('#name').html('<h6>'+data.value+'<h6>');
//            			}
                    	
                    	if(data.value == 'true')
                    	{
                    		window.location.href = '/Dashboard';
                    	}
                    	else
                    	{
                    		$('#name').html('<h6>'+'User doesnot exist.Plz SignUp'+'<h6>');
                    	}
                    	
                    },
                    dataType : 'json'
                });
        	}   
        };
        </script>
        
        <br>
            <div class="inputBox">
                <input type="email" id="logEmail" name="email" required="">
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
        	<form action="/SignUp">
        		<input type="submit" name="" id= "myButton" value="New User" >
        	</form>
            </div>
          
    </div>
</body>

</html>