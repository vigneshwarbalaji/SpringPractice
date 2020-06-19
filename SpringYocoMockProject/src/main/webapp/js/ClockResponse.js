
$.ajax({
	type: 'get',
    url: '/GetEntry',
    success: function (data, status, xhr) {
    	
    	let x = true;
    	let j = 1;

    	for(let i = 0; i <data.user.length;i++)
    		{
    		if(i == 0)
    			{
    			$('.tabcont').append('<tbody><tr><th colspan="5">'+data.date[i]+'</th><tr></tbody>')
    			}
    		
	    		if(data.user == null)
	    		{
	    			$("#clockOutInput").hide();
	    		}
	    	else if(data.user[i].clockOut == "ongoing")
	    		{
	    			x = false;
	    			
//	    			var hours = Math.abs(parseInt(data.user[i].clockIn).split(':') - parseInt(data.user[i].clockOut).split(':')); 
	    			$('.tabcont').append('<tr><td>'+data.user[i].project+'</td><td>'+data.user[i].taskDescription+'</td><td>'+data.user[i].clockIn+'</td><td class = "clockout">'+data.user[i].clockOut+'</td><td class = "totalHours"></td></tr>');
	    			
	    		}
	    	else
	    		{
	    		$('.tabcont').append('<tr><td>'+data.user[i].project+'</td><td>'+data.user[i].taskDescription+'</td><td>'+data.user[i].clockIn+'</td><td>'+data.user[i].clockOut+'</td><td>'+data.diffInHours[i]+'h'+data.diffInMins[i]+'m'+data.diffInSecs[i]+'s'+'</td></tr>');
	    		
	    		//.tabcont
	    			if(data.date[j - 1] != data.date[j] && j < data.user.length)
	    			{
	    				$('.tabcont').prepend('<tbody><tr><th colspan="5">'+data.date[j]+'</th><tr></tbody>')
	    			}
	    		}
	    		
	    		++j;
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
	
	$.post('/clockIn', {project: $('#project').val(),descript: $('#descript').val()}, 
			function (data, textStatus, jqXHR) {
		
		$('#project').val('');
		$('#descript').val('');
		//console.log(data.userList);
		if(data.value == 'false')
			{
				$('#warn').html('<h6>You had already clocked in</h6>');
			}
		else
			{
			
			if(data.date != null)
			{
				$('.tabcont').prepend('<tbody><tr><th colspan="5">'+data.date+'</th><tr></tbody>')
			}
				$('.tabcont').prepend('<tr><td>'+data.userList.project+'</td><td>'+data.userList.taskDescription+'</td><td>'+data.userList.clockIn+'</td><td class = "clockout">'+data.userList.clockOut+'</td><td class = "totalHours"></td></tr>');
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
            url: '/clockOut',
            success: function (data, status, xhr) {
            	
            	if(data.value == 'false')
    			{
    				$('#warn').html('<h6>You had already clocked out</h6>');
    			}
    		else if(data.checkIfUserClockedIn == 'false'){
    			$('#warn').html('<h6>Please clock In first</h6>');
    		}
    		else
    			{
    						$('.clockout').first().text(data.userList.clockOut);
    						
    						$('.totalHours').first().text(data.hours+'h'+data.mins+'m'+data.secs+'s');
    			}
            	
            },
            dataType : 'json'
        });
    });
});




$(function () {
    $('#burst').on('click', function () {

    	//var Status = $(this).val();
        $.ajax({
        	type: 'GET',
            url: '/task',
            success: function (data, status, xhr) {
    				$('#warn').html('<h6>Infected</h6>');
            	
            },
            dataType : 'json'
        });
    });
});

