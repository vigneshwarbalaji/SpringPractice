/**
 * 
 */

function addNumbers()
{
	var xhr = new XMLHttpRequest();
	
    xhr.open('GET','http://localhost:8080/AddController?numOne='+document.getElementById("firstNo").value+'&numTwo='+document.getElementById("secondNo").value, true);
   

    xhr.send();

    xhr.onload = function() {
    	
        let responseobj = JSON.parse(this.response);
        
        //console.log(responseobj);
        
        
        if (this.status == 200) {
        	
        	document.getElementById('result').value = 	responseobj;
        	document.getElementById('res').value = 	responseobj;
       	
        } else if (this.status == 404) {
            document.getElementById('result').value = '<h1>Not Found -- 404 Error</h1>'
        }
    }
}
