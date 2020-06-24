function addNumbers()
	{
	var xhr = new XMLHttpRequest();

	//xhr.setRequestHeader("Content-Type", "application/json");
    xhr.open('GET','http://localhost:8080/add?numOne='+document.getElementById("firstNo").value+'&numTwo='+document.getElementById("secondNo").value, true);
    
    
    xhr.send();
    
    xhr.onload = function() {
        
    	
        let responseobj = JSON.parse(this.response);
        if (this.status == 200) {
        	console.log(responseobj);
            document.getElementById('res').value = responseobj.value;
        } else if (this.status == 404) {
            document.getElementById('res').value = '<h1>Not Found -- 404 Error</h1>'
        }
    }
    
	}


function subNumbers()
{
var xhr = new XMLHttpRequest();

//xhr.setRequestHeader("Content-Type", "application/json");
xhr.open('GET','http://localhost:8080/sub?numOne='+document.getElementById("firstNo").value+'&numTwo='+document.getElementById("secondNo").value, true);


xhr.send();

xhr.onload = function() {
    
	
    let responseobj = JSON.parse(this.response);
    if (this.status == 200) {
    	console.log(responseobj);
        document.getElementById('res').value = responseobj.value;
    } else if (this.status == 404) {
        document.getElementById('res').value = '<h1>Not Found -- 404 Error</h1>'
    }
}

}


function MultiNumbers()
{
var xhr = new XMLHttpRequest();

//xhr.setRequestHeader("Content-Type", "application/json");
xhr.open('GET','http://localhost:8080/mul?numOne='+document.getElementById("firstNo").value+'&numTwo='+document.getElementById("secondNo").value, true);


xhr.send();

xhr.onload = function() {
    
	
    let responseobj = JSON.parse(this.response);
    if (this.status == 200) {
    	console.log(responseobj);
        document.getElementById('res').value = responseobj.value;
    } else if (this.status == 404) {
        document.getElementById('res').value = '<h1>Not Found -- 404 Error</h1>'
    }
}

}


function divNumbers()
{
var xhr = new XMLHttpRequest();

//xhr.setRequestHeader("Content-Type", "application/json");
xhr.open('GET','http://localhost:8080/div?numOne='+document.getElementById("firstNo").value+'&numTwo='+document.getElementById("secondNo").value, true);


xhr.send();

xhr.onload = function() {
    
	
    let responseobj = JSON.parse(this.response);
    if (this.status == 200) {
    	console.log(responseobj);
        document.getElementById('res').value = responseobj.value;
    } else if (this.status == 404) {
        document.getElementById('res').value = '<h1>Not Found -- 404 Error</h1>'
    }
}

}