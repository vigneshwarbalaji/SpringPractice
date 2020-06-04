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
    	
    	let x = true;
    	
    	for(let i = 0; i <data.user.length;i++)
    		{
	    		if(data.user == null)
	    		{
	    			$("#clockOutInput").hide();
	    		}
	    	else if(data.user[i].clockOut == "ongoing")
	    		{
	    			x = false;
//	    			$('#one').html(data.user.project);
//	    			$('#two').html(data.user.taskDescription);
//	    			$('#three').html(data.user.clockIn);
//	    			$('#four').html(data.user.clockOut);
	    			
	    			$('.tabcont').append('<tr><td>'+data.user[i].project+'</td><td>'+data.user[i].taskDescription+'</td><td>'+data.user[i].clockIn+'</td><td>'+data.user[i].clockOut+'</td></tr>');
	    			
	//    			$('#rows').append('<tr><td></td></tr>')
	    		}
	    	else
	    		{
//		    		$("#clockOutInput").hide();
//					$('#one').html(data.user.project);
//					$('#two').html(data.user.taskDescription);
//					$('#three').html(data.user.clockIn);
//					$('#four').html(data.user.clockOut);
		    		$('.tabcont').append('<tr><td>'+data.user[i].project+'</td><td>'+data.user[i].taskDescription+'</td><td>'+data.user[i].clockIn+'</td><td>'+data.user[i].clockOut+'</td></tr>');
	    		}
    		}
    	
    	
    	if(x == false)
    		{
    		console.log(x);
    			$("#clockInInput").hide();
    		}
    	else
    		{	
    		console.log(x);
    			$("#clockOutInput").hide();
    		}
			
    },
    dataType : 'json'
});


$('#clockInInput').click(function(){
	
	$("#clockInInput").hide();
	$("#clockOutInput").show();
	
	$.post('http://localhost:8080/clockIn', {project: $('#project').val(),descript: $('#descript').val()}, 
			function (data, textStatus, jqXHR) {
		
		//console.log(data.userList);
		if(data.value == 'false')
			{
				$('#warn').html('<h6>You had already clocked in</h6>');
			}
		else
			{
				//$('#name').html(data.value);
//				$('#one').html(data.project);
//				$('#two').html(data.taskDescription);
//				$('#three').html(data.clockIn);
//				$('#four').html(data.clockOut);
			
//			for(let i = 0;i < data.userList.length;i++)
//			{
				$('.tabcont').append('<tr><td>'+data.userList.project+'</td><td>'+data.userList.taskDescription+'</td><td>'+data.userList.clockIn+'</td><td class = "clockout">'+data.userList.clockOut+'</td></tr>');
//				$('<tr><td>'+data.userList[i].project+'</td><td>'+data.userList[i].taskDescription+'</td><td>'+data.userList[i].clockIn+'</td><td>'+data.userList[i].clockOut+'</td></tr>').insertAfter($(this).closest('tr'));
//			}
			}
		
	},'json');
});



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
            	
            	//console.log(data.userList);
            	
            	if(data.value == 'false')
    			{
    				$('#warn').html('<h6>You had already clocked out</h6>');
    			}
    		else if(data.checkIfUserClockedIn == 'false'){
    			$('#warn').html('<h6>Please clock In first</h6>');
    		}
    		else
    			{
//    				$('#one').html(data.project);
//    				$('#two').html(data.taskDescription);
//    				$('#three').html(data.clockIn);
//    				$('#four').html(data.clockOut);
    			
//    				for(let i = 0;i < data.userList.length;i++)
//    				{
    				//console.log("test1"+data.userList[0].clockOut);
//    					$('#rows').after().remove();
//    					$('td:last-child').remove();
//    					$('#rows').after('<tr><td>'+data.userList[0].project+'</td><td>'+data.userList[0].taskDescription+'</td><td>'+data.userList[0].clockIn+'</td><td>'+data.userList[0].clockOut+'</td></tr>');
    			
//    					if($('#clockout').text() == "ongoing")
//    						{
    						$('.clockout').last().text(data.userList.clockOut);
//    						}
    					
//    					$('<tr><td>'+data.userList[i].project+'</td><td>'+data.userList[i].taskDescription+'</td><td>'+data.userList[i].clockIn+'</td><td>'+data.userList[i].clockOut+'</td></tr>').insertAfter($(this).closest('tr'));
//    				}
    			}
            	
            },
            dataType : 'json'
        });
    });
});

