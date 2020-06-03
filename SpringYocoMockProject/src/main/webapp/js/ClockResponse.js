/**
 * 
 */


//function clockInFunc()

//{
//	var xhr = new XMLHttpRequest();
//
//	//var action = "ClockIn";
//	//var project = document.getElementById("project").value;
//	//var descript = document.getElementById("descript").value;
//	
//	
//    xhr.open('POST','http://localhost:8080/DashController?pro='+document.getElementById("project").value+'&descr='+document.getElementById("descript").value, true);
//   // xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
//    
//    //xhr.send("name="+name+"&email="+email+"&pass="+pass+"&rePass="+rePass);
//
//    xhr.send();
//
//    xhr.onload = function() {
//    	
//        let responseobj = JSON.parse(this.response);
//        
//        
//        if (this.status == 200) {
//        	
//        	console.log(responseobj);
//        	if(responseobj.value == 'false')
//        		{
//        		document.getElementById('warn').innerHTML = '<h6>You had already clocked in</h6>';
//        		}
//        	else
//        		{
//        		//for(let i = 0;i < responseobj.length;i++)
//        			//{
////	        			document.getElementById('one').innerHTML = '<tr><td>responseobj[i].project<\td>';
////	            		document.getElementById('two').innerHTML = '<td>responseobj[i].taskDescription<\td>';
////	            		document.getElementById('three').innerHTML = '<td>responseobj[i].clockIn<\td>';
////	            		document.getElementById('four').innerHTML = '<td>responseobj[i].clockOut<\td><\tr>';
//        			//}
//        		
//        		document.getElementById('one').innerHTML = responseobj.project;
//        		document.getElementById('two').innerHTML = responseobj.taskDescription;
//        		document.getElementById('three').innerHTML = responseobj.clockIn;
//        		//document.getElementById('four').innerHTML = responseobj.clockOut;
//        		
//        		}
//        		
//        } else if (this.status == 404) {
//            document.getElementById('name').innerHTML = '<h1>Not Found -- 404 Error</h1>'
//        }
//    }
//}


//DashController

//$("#clockOutInput").hide();

$.ajax({
	type: 'get',
    url: 'http://localhost:8080/GetEntry',
    success: function (data, status, xhr) {
//        $('p').append('status: ' + status + ', data: ' + data);
    	
			//$('#name').html(data.value);
    	
    	if(data.user.clockOut == "ongoing")
    		{
    			$("#clockInInput").hide();
    			$('#one').html(data.user.project);
    			$('#two').html(data.user.taskDescription);
    			$('#three').html(data.user.clockIn);
    			$('#four').html(data.user.clockOut);
    		}
    	else
    		{
    		$("#clockOutInput").hide();
			$('#one').html(data.user.project);
			$('#two').html(data.user.taskDescription);
			$('#three').html(data.user.clockIn);
			$('#four').html(data.user.clockOut);
    		}
			
    },
    dataType : 'json'
});


$('#clockInInput').click(function(){
	
	$("#clockInInput").hide();
	$("#clockOutInput").show();
	
	$.post('http://localhost:8080/clockIn', {project: $('#project').val(),descript: $('#descript').val()}, 
			function (data, textStatus, jqXHR) {
		
		if(data.value == 'false')
			{
				$('#warn').html('<h6>You had already clocked in</h6>');
			}
		else
			{
				//$('#name').html(data.value);
				$('#one').html(data.project);
				$('#two').html(data.taskDescription);
				$('#three').html(data.clockIn);
				$('#four').html(data.clockOut);
			}
		
	},'json');
});


/*$('#clockOutInput').click(function(){
	
	$.put('http://localhost:8080/clockOut', 
			function (data, textStatus, jqXHR) {
		
		if(data.value == 'false')
			{
				$('#warn').html('<h6>You had already clocked out</h6>');
			}
		else if(data.checkIfUserClockedIn == 'false'){
			$('#warn').html('<h6>Please clock In first</h6>');
		}
		else
			{
				//$('#name').html(data.value);
				$('#one').html(data.project);
				$('#two').html(data.taskDescription);
				$('#three').html(data.clockIn);
			
			$('#four').html(data.clockOut);
			}
		
	},'json');
});*/

//$.ajax({
//    type: 'PUT',
//    url: 'http://localhost:8080/clockOut',
//    success: function (data) {
//    	if(data.value == 'false')
//		{
//			$('#warn').html('<h6>You had already clocked out</h6>');
//		}
//	else if(data.checkIfUserClockedIn == 'false'){
//		$('#warn').html('<h6>Please clock In first</h6>');
//	}
//	else
//		{
//		//$('#name').html(data.value);
//		$('#one').html(data.project);
//		$('#two').html(data.taskDescription);
//		$('#three').html(data.clockIn);
//	
//	$('#four').html(data.clockOut);
//		}
//    }
//});


$(function () {
    $('#clockOutInput').on('click', function () {

    	$("#clockInInput").show();
    	$("#clockOutInput").hide();
    	//var Status = $(this).val();
        $.ajax({
        	type: 'PUT',
            url: 'http://localhost:8080/clockOut',
            success: function (data, status, xhr) {
//                $('p').append('status: ' + status + ', data: ' + data);
            	
            	
            	if(data.value == 'false')
    			{
    				$('#warn').html('<h6>You had already clocked out</h6>');
    			}
    		else if(data.checkIfUserClockedIn == 'false'){
    			$('#warn').html('<h6>Please clock In first</h6>');
    		}
    		else
    			{
    				//$('#name').html(data.value);
    				$('#one').html(data.project);
    				$('#two').html(data.taskDescription);
    				$('#three').html(data.clockIn);
    			
    			$('#four').html(data.clockOut);
    			}
            	
            	
            },
            dataType : 'json'
        });
    });
});























//function clockOutFunc()
//{
//	var xhr = new XMLHttpRequest();
//
//	//var action = "ClockIn";
//	//var project = document.getElementById("project").value;
//	//var descript = document.getElementById("descript").value;
//	
//	
//    xhr.open('PATCH','http://localhost:8080/DashController', true);
//   // xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
//    
//    //xhr.send("name="+name+"&email="+email+"&pass="+pass+"&rePass="+rePass);
//
//    xhr.send();
//
//    xhr.onload = function() {
//    	
//        let responseobj = JSON.parse(this.response);
//        
//        
//        if (this.status == 200) {
//        	
//        	console.log(responseobj);
////        		document.getElementById('one').innerHTML = responseobj.project;
////        		document.getElementById('two').innerHTML = responseobj.taskDescription;
////        		document.getElementById('three').innerHTML = responseobj.clockIn;
//        	if(responseobj == 'false')
//        		{
//        		document.getElementById('warn').innerHTML = "<h6> You must clock in first</h6>";
//        		}
//        	else
//        		{
//        		document.getElementById('one').innerHTML = responseobj.project;
//        		document.getElementById('two').innerHTML = responseobj.taskDescription;
//        		document.getElementById('three').innerHTML = responseobj.clockIn;
//        		document.getElementById('four').innerHTML = responseobj.clockOut;
//        		}
//        	
//        } else if (this.status == 404) {
//            document.getElementById('name').innerHTML = '<h1>Not Found -- 404 Error</h1>'
//        }
//    }
//}