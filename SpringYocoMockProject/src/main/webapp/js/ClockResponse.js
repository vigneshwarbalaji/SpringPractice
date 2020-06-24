
var date = "Current Entries";
var check = false;


//$.ajax({
//	type: 'get',
//    url: '/GetEntry',
//    success: function (data, status, xhr) {
//    	
//    	let x = true;
//    	let j = 1;
//
////    	let j = (data.user.length) - 1;
//    	for(let i = 0 ; i < data.user.length ;i++)
//    		{
//    		if(i == 0)
//    			{
//    			$('.tabcont').append('<tbody><tr><th colspan="5">'+data.date[i]+'</th><tr></tbody>')
//    			}
//    		
//	    		if(data.user == null)
//	    		{
//	    			$("#clockOutInput").hide();
//	    		}
//	    	else if(data.user[i].clockOut == "ongoing")
//	    		{
//	    			x = false;
//	    			
////	    			var hours = Math.abs(parseInt(data.user[i].clockIn).split(':') - parseInt(data.user[i].clockOut).split(':')); 
//	    			$('.tabcont').append('<tr><td>'+data.user[i].project+'</td><td>'+data.user[i].taskDescription+'</td><td>'+data.user[i].clockIn+'</td><td class = "clockout">'+data.user[i].clockOut+'</td><td class = "totalHours"></td></tr>');
//	    			
//	    		}
//	    	else
//	    		{
//	    		
//	    		
//	    		$('.tabcont').append('<tr><td>'+data.user[i].project+'</td><td>'+data.user[i].taskDescription+'</td><td>'+data.user[i].clockIn+'</td><td>'+data.user[i].clockOut+'</td><td>'+data.diffInHours[i]+'h'+data.diffInMins[i]+'m'+data.diffInSecs[i]+'s'+'</td></tr>');
//	    		
//	    		//.tabcont
//	    			if(data.date[j - 1] != data.date[j] && j < data.user.length)
//	    			{
//	    				
//	    				if(data.date[j] == undefined)
//	    					{
//	    					
////	    					check = true;
////	    					$('.tabcont').prepend('<tbody><tr id = "current"><th colspan="5">'+date+'</th><tr></tbody>')
//	    					}
//	    				else
//	    					{
////	    					$('#rem').remove()
//	    					$('.tabcont').prepend('<tbody><tr><th colspan="5">'+data.date[j]+'</th><tr></tbody>')
//	    					}
////	    				$('.tabcont').prepend('<tbody><tr><th colspan="5">'+data.date[j]+'</th><tr></tbody>')
//	    				
////	    				date = data.date[j]
//	    			}
//	    		}
//	    		
//	    		++j;
////	    		--j;
//    		}
//    	
//    	
//    	if(x == false)
//    		{
//    		console.log(x);
//    			$("#clockInInput").hide();
//    		}
//    	else
//    		{	
//    		console.log(x);
//    			$("#clockOutInput").hide();
//    		}
//			
//    },
//    dataType : 'json'
//});



$.ajax({
	type: 'get',
    url: '/GetEntry',
    success: function (data, status, xhr) {
    	
    	let x = true;
    	
    	let j = 1;
//    	let count = 0;

//    	let j = (data.user.length) - 2;
//    	for(let i = (data.user.length-1) ; i >= 0  ;i--)
    	for(let i = 0 ; i <  data.user.length;i++)
    		{
//    		if(i == (data.user.length) - 1)
//    			{
//    			$('.tabcont').append('<tbody><tr><th colspan="5">'+data.date[i]+'</th><tr></tbody>')
//    			}
    		
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
	    		
	    		
	    		$('.tabcont').prepend('<tr><td>'+data.user[i].project+'</td><td>'+data.user[i].taskDescription+'</td><td>'+data.user[i].clockIn+'</td><td>'+data.user[i].clockOut+'</td><td>'+data.diffInHours[i]+'h'+data.diffInMins[i]+'m'+data.diffInSecs[i]+'s'+'</td></tr>');
//	    		console.log(data.diffInHours[i]+'   '+data.diffInMins[i]+'    '+data.diffInSecs[i]+'  '+i+'   '+count);++count;
	    		//.tabcont
	    			if(data.date[j - 1] != data.date[j] && j >= 0)
	    			{
	    							
	    					$('.tabcont').prepend('<tbody><tr id= "current"><th colspan="5">'+data.date[j-1]+'</th><tr></tbody>')
	    				
	    				date = data.date[j - 1]
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
//			if(date != data.date)
//			{
//			if(check == false)
//				{
//				check = true;
				
//				$('.tabcont').prepend('<tbody><tr><th colspan="5">'+date+'</th><tr></tbody>')
//				}
//				date = data.date;
				
//			}
//				$('.tabcont').prepend('<tr><td>'+data.userList.project+'</td><td>'+data.userList.taskDescription+'</td><td>'+data.userList.clockIn+'</td><td class = "clockout">'+data.userList.clockOut+'</td><td class = "totalHours"></td></tr>');
			
			
			
			if(date != data.date)
			{
				$('.tabcont').prepend('<tbody><tr><th colspan="5">'+date+'</th><tr></tbody>')
				
				date = data.date;
				
				console.log('test  '+date+'   '+data.date);
			}
			
			
			
			$('.tabcont').prepend('<tr><td>'+data.userList.project+'</td><td>'+data.userList.taskDescription+'</td><td>'+data.userList.clockIn+'</td><td class = "clockout">'+data.userList.clockOut+'</td><td class = "totalHours"></td></tr>');
			
			$('#current').remove();
			$('.tabcont').prepend('<tbody><tr id = "current"><th colspan="5">'+date+'</th><tr></tbody>')
			
			console.log('new test '+date+'    '+data.date);
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
    $('#confirm').on('click',function () {

    	
    	var zone = $('#zone').val();
    	console.log(zone);
        $.ajax({
        	type: 'GET',
            url: '/GetEntry',
            data :{ zone : $('#zone').val()},
            success: function (data, status, xhr) {
            	
            	let x = true;
            	let j = 1;
            	$('.tabcont tbody').remove();
            	for(let i = 0; i <data.user.length;i++)
            		{
//            		if(i == 0)
//            			{
//            			$('.tabcont').append('<tbody><tr><th colspan="5">'+data.date[i]+'</th><tr></tbody>')
//            			}
            		
        	    		if(data.user == null)
        	    		{
        	    			$("#clockOutInput").hide();
        	    		}
        	    	else if(data.user[i].clockOut == "ongoing")
        	    		{
        	    			x = false;
        	    			
//        	    			var hours = Math.abs(parseInt(data.user[i].clockIn).split(':') - parseInt(data.user[i].clockOut).split(':')); 
        	    			$('.tabcont').append('<tr><td>'+data.user[i].project+'</td><td>'+data.user[i].taskDescription+'</td><td>'+data.user[i].clockIn+'</td><td class = "clockout">'+data.user[i].clockOut+'</td><td class = "totalHours"></td></tr>');
        	    			
        	    		}
        	    	else
        	    		{
        	    		$('.tabcont').prepend('<tr><td>'+data.user[i].project+'</td><td>'+data.user[i].taskDescription+'</td><td>'+data.user[i].clockIn+'</td><td>'+data.user[i].clockOut+'</td><td>'+data.diffInHours[i]+'h'+data.diffInMins[i]+'m'+data.diffInSecs[i]+'s'+'</td></tr>');
        	    		
        	    		//.tabcont  < data.user.length
        	    			if(data.date[j - 1] != data.date[j] && j >= 0)
        	    			{
        	    				$('.tabcont').prepend('<tbody><tr><th colspan="5">'+data.date[j - 1]+'</th><tr></tbody>')
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






$.ajax({
	type: 'get',
    url: 'http://api.timezonedb.com/v2.1/list-time-zone?key=J3SYS1NNO9NW&format=json',
    success: function (data, status, xhr) {
    	
    	var sample = data.zones;

        sample.sort(function(a, b) { return a.gmtOffset - b.gmtOffset });

        for (let i = 0; i < sample.length; i++) {

            // let sample = Math.floor(data.zones[0].timestamp - data.zones[0].gmtOffset) / 3600

            let hour = Math.floor((sample[i].gmtOffset) % (3600 * 24) / 3600);
            var minute = Math.abs(sample[i].gmtOffset % 3600 / 60);

            
//            console.log(hour + ":" + minute);
            // Math.floor(d / 3600)

            // $('.tabcont').append('<tr><td>' + data.zones[i].countryCode + '</td><td>' + data.zones[i].countryName + '</td><td>' + data.zones[i].zoneName + '</td><td>' + data.zones[i].gmtOffset + '</td><td>' + data.zones[i].timestamp + '</td></tr>');

            
            if(Math.sign(hour) == 1)
            {
            	$('#zone').append('<option value='+sample[i].zoneName+'> GMT ( +' + hour + ":" + minute + ' ) ' + sample[i].zoneName + '</option><br>');
            }
            else
            {
            	$('#zone').append('<option value='+sample[i].zoneName+'> GMT ( ' + hour + ":" + minute + ' ) ' + sample[i].zoneName + '</option><br>');
            }
            
        }

			
    },
    dataType : 'json'
});