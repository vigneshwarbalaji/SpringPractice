function createFunc()
	{
	var xhr = new XMLHttpRequest();

	var name = document.getElementById("signName").value;
	var email = document.getElementById("signMail").value;
	var pass =	document.getElementById("signPass").value;
	var rePass = document.getElementById("signRePass").value;
	
    xhr.open('POST','http://localhost:8080/ControllerServlet', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    
    xhr.send("name="+name+"&email="+email+"&pass="+pass+"&rePass="+rePass);


    xhr.onload = function() {
    	
        let responseobj = this.responseText;
        if (this.status == 200) {
        	if(responseobj == "true")
        		{
        			window.location.href = "Dashboard.jsp";
        		}
        	else
        		{
        		document.getElementById('name').innerHTML = responseobj;
        		}
            
        } else if (this.status == 404) {
            document.getElementById('name').innerHTML = '<h1>Not Found -- 404 Error</h1>'
        }
    }
    
}

function loginFunc()
{
var xhr = new XMLHttpRequest();


//var email = document.getElementById("logEmail").value;
//var pass =	document.getElementById("logPass").value;


xhr.open('GET','http://localhost:8080/ControllerServlet?email='+document.getElementById("logEmail").value+"&pass="+document.getElementById("logPass").value, true);
//xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

xhr.send();


xhr.onload = function() {
    
	
    //let responseobj = this.response;
    var responseobj = this.responseText;


    	
	    if (this.status == 200) {
	    	//console.log(this.responseText);
	    	
	    	if(responseobj == "true")
	    		{
	    			window.location.href = "Dashboard.jsp";
	    		//	window.location.href = "/DashController";
	    		}
	    	else
	    		{
	    			document.getElementById('name').innerHTML = responseobj;
	    		}
	        
	    }
	    else if (this.status == 404) {
	        document.getElementById('name').innerHTML = '<h1>Not Found -- 404 Error</h1>'
	    }
	//}
}
}

