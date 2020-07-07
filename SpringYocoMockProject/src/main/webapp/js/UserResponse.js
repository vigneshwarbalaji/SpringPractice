//function createFunc()
//	{
//	var xhr = new XMLHttpRequest();
//
//	var name = document.getElementById("signName").value;
//	var email = document.getElementById("signMail").value;
//	var pass =	document.getElementById("signPass").value;
//	var rePass = document.getElementById("signRePass").value;
//	
//    xhr.open('POST','http://localhost:8080/ControllerServlet', true);
//    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
//    
//    xhr.send("name="+name+"&email="+email+"&pass="+pass+"&rePass="+rePass);
//
//
//    xhr.onload = function() {
//    	
//        let responseobj = JSON.parse(this.response);
//        if (this.status == 200) {
//        	if(responseobj.value == "true")
//        		{
//        			window.location.href = "/Dashboard";
//        		}
//        	else
//        		{
//        		document.getElementById('name').innerHTML = responseobj.value;
//        		}
//            
//        } else if (this.status == 404) {
//            document.getElementById('name').innerHTML = '<h1>Not Found -- 404 Error</h1>'
//        }
//    }
//    
//}

//function loginFunc()
//{
//var xhr = new XMLHttpRequest();
//
//
////var email = document.getElementById("logEmail").value;
////var pass =	document.getElementById("logPass").value;
//
//
//xhr.open('GET','http://localhost:8080/ControllerServlet?email='+document.getElementById("logEmail").value+"&pass="+document.getElementById("logPass").value, true);
////xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
//
//xhr.send();
//
//
//xhr.onload = function() {
//    
//	
//    //let responseobj = this.response;
//    var responseobj = JSON.parse(this.response);
//
//
//    	
//	    if (this.status == 200) {
//	    	//console.log(this.responseText);
//	    	
//	    	if(responseobj.value == "true")
//	    		{
//	    			window.location.href = "/Dashboard";
//	    		//	window.location.href = "/DashController";
//	    		}
//	    	else
//	    		{
//	    			document.getElementById('name').innerHTML = responseobj.value;
//	    		}
//	        
//	    }
//	    else if (this.status == 404) {
//	        document.getElementById('name').innerHTML = '<h1>Not Found -- 404 Error</h1>'
//	    }
//
//}
//}


$('#signSub').click(function(){
	
	$.post('/ControllerServlet', {name: $('#signName').val(),email: $('#signMail').val(),pass: $('#signPass').val(),rePass:$('#signRePass').val()}, 
			function (data, textStatus, jqXHR) {
		
		if(data.value == 'true')
			{
			window.location.href = '/Dashboard';
			}
		else
			{
				$('#name').html('<h6>'+data.value+'<h6>');
			}
		
	},'json');
});



$('#logSub').click(function(){
	
	$.get('/ControllerServlet', {email: $('#logEmail').val(),pass: $('#logPass').val()}, 
			function (data, textStatus, jqXHR) {
		
		if(data.value == 'true')
			{
				window.location.href = '/Dashboard';
			}
		else
			{
				$('#name').html('<h6>'+data.value+'</h6>');
			}
		
	},'json');
});


/*

function onSignIn(googleUser) {
    // Useful data for your client-side scripts:
//    var profile = googleUser.getBasicProfile();
//    console.log("ID: " + profile.getId()); // Don't send this directly to your server!
//    console.log('Full Name: ' + profile.getName());
//    console.log('Given Name: ' + profile.getGivenName());
//    console.log('Family Name: ' + profile.getFamilyName());
//    console.log("Image URL: " + profile.getImageUrl());
//    console.log("Email: " + profile.getEmail());
//
    // The ID token you need to pass to your backend:
    
    console.log("ID Token: " + id_token);
  }

*/



/*
$('#googleSign').click(function(googleUser){
	
    var profile = googleUser.getBasicProfile();

	var id_token = googleUser.getAuthResponse().id_token;
    	
	console.log("cat");
	console.log("test 1"+id_token);
        $.ajax({
        	type: 'POST',
            url: '/googleSignUp',
            
            
				data:{
					googleToken : id_token
				},
            success: function (data, status, xhr) {
            	
//            	if(data.value == 'true')
//    			{
//            		window.location.href = '/Dashboard';
//    			}
//    		else
//    			{
//    			$('#name').html('<h6>'+data.value+'<h6>');
//    			}
            	
            	if(data.value == 'true')
            	{
            		window.location.href = '/Dashboard';
            	}
            	
            },
            dataType : 'json'
        });
});

*/