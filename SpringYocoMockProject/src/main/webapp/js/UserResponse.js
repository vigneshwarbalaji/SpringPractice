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
	
	$.post('http://localhost:8080/ControllerServlet', {name: $('#signName').val(),email: $('#signMail').val(),pass: $('#signPass').val(),rePass:$('#signRePass').val()}, 
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
	
	$.get('http://localhost:8080/ControllerServlet', {email: $('#logEmail').val(),pass: $('#logPass').val()}, 
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

