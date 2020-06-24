/**
 * 
 */


function clockInFunc()
{
	var xhr = new XMLHttpRequest();

	//var action = "ClockIn";
	//var project = document.getElementById("project").value;
	//var descript = document.getElementById("descript").value;
	
	
    xhr.open('POST','http://localhost:8080/DashController?pro='+document.getElementById("project").value+'&descr='+document.getElementById("descript").value, true);
   // xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    
    //xhr.send("name="+name+"&email="+email+"&pass="+pass+"&rePass="+rePass);

    xhr.send();

    xhr.onload = function() {
    	
        let responseobj = JSON.parse(this.response);
        
        
        if (this.status == 200) {
        	
        	console.log(responseobj);
        	if(responseobj == 'false')
        		{
        		document.getElementById('warn').innerHTML = '<h6>You had already clocked in</h6>';
        		}
        	else
        		{
        		//for(let i = 0;i < responseobj.length;i++)
        			//{
//	        			document.getElementById('one').innerHTML = '<tr><td>responseobj[i].project<\td>';
//	            		document.getElementById('two').innerHTML = '<td>responseobj[i].taskDescription<\td>';
//	            		document.getElementById('three').innerHTML = '<td>responseobj[i].clockIn<\td>';
//	            		document.getElementById('four').innerHTML = '<td>responseobj[i].clockOut<\td><\tr>';
        			//}
        		
        		document.getElementById('one').innerHTML = responseobj.project;
        		document.getElementById('two').innerHTML = responseobj.taskDescription;
        		document.getElementById('three').innerHTML = responseobj.clockIn;
        		document.getElementById('four').innerHTML = responseobj.clockOut;
        		
        		}
        		
        } else if (this.status == 404) {
            document.getElementById('name').innerHTML = '<h1>Not Found -- 404 Error</h1>'
        }
    }
}


function clockOutFunc()
{
	var xhr = new XMLHttpRequest();

	//var action = "ClockIn";
	//var project = document.getElementById("project").value;
	//var descript = document.getElementById("descript").value;
	
	
    xhr.open('GET','http://localhost:8080/DashController', true);
   // xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    
    //xhr.send("name="+name+"&email="+email+"&pass="+pass+"&rePass="+rePass);

    xhr.send();

    xhr.onload = function() {
    	
        let responseobj = JSON.parse(this.response);
        
        
        if (this.status == 200) {
        	
        	console.log(responseobj);
//        		document.getElementById('one').innerHTML = responseobj.project;
//        		document.getElementById('two').innerHTML = responseobj.taskDescription;
//        		document.getElementById('three').innerHTML = responseobj.clockIn;
        	if(responseobj == 'false')
        		{
        		document.getElementById('warn').innerHTML = "<h6> You must clock in first</h6>";
        		}
        	else
        		{
        		document.getElementById('one').innerHTML = responseobj.project;
        		document.getElementById('two').innerHTML = responseobj.taskDescription;
        		document.getElementById('three').innerHTML = responseobj.clockIn;
        		document.getElementById('four').innerHTML = responseobj.clockOut;
        		}
        	
        } else if (this.status == 404) {
            document.getElementById('name').innerHTML = '<h1>Not Found -- 404 Error</h1>'
        }
    }
}
