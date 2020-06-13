$(function () {
    $('#Add').on('click', function () {
    	
        $.ajax({
        	type: 'POST',
            url: 'http://localhost:8080/AddTask',
				data:{
					task: $('#addTask').val(),
					descr: $('#taskDescription').val()
				},
            success: function (data, status, xhr) {
            	$('#addTask').val('');
            	$('#taskDescription').val('');
            	
            	if(data.value == 'true')
    			{
//            		window.location.href = '/DashController';
            		
            		$('#demo').html('<h6>'+'Task added successfully'+'<h6>');
            		$('.tableContainer').append('<tr><td>'+data.taskList.task+'</td><td>'+data.taskList.taskDescription+'</td><tr>');
    			}
    		else
    			{
    				$('#name').html('<h6>'+data.value+'<h6>');
    			}
            	
            },
            dataType : 'json'
        });
    });
});


$(function () {
//    $('#View').on('click', function () {
    	
        $.ajax({
        	type: 'GET',
            url: 'http://localhost:8080/ViewTask',
//				data:{
//					task: $('#addTask').val(),
//					descr: $('#taskDescription').val()
//				},
            success: function (data, status, xhr) {
            	
            	if(data.value == 'true')
    			{
//            		console.log(data.value);
//            		
//            		console.log(data.taskList[0].task);
//            		console.log(data.taskList[1].task);
//            		window.location.href = '/DashController';
            		for(let i = 0;i < data.taskList.length;i++)
            		{
            			$('.tableContainer').append('<tr><td>'+data.taskList[i].task+'</td><td>'+data.taskList[i].taskDescription+'</td><tr>');
            		}
    			}
    		else
    			{
    				$('#name').html('<h6>'+'Failed to add task'+'<h6>');
    			}
            	
            },
            dataType : 'json'
        });
//    });
});




$(function () {
    $('#logOut').on('click', function () {
    	
        $.ajax({
        	type: 'GET',
            url: 'http://localhost:8080/logOut',
//            contentType: "application/json",

            success: function (data, status, xhr) {
            	
            	if(data.value == 'true')
            	{
            		window.location.href = 'http://localhost:8080';
    			}
    		else
    			{
    				$('#name').html('<h6>'+'Failed to add task'+'<h6>');
    			}
            	
            },
            dataType : 'json'
        });
    });
});