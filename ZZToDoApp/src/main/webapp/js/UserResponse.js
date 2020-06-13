
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


//$(function () {
//    $('#signSub').on('click', function () {
//    	
//        $.ajax({
//        	type: 'POST',
//            url: '/api/ControllerServlet',
//            data:
//            success: function (data, status, xhr) {
//            	
//            	if(data.value == 'true')
//    			{
//            		window.location.href = 'Dashboard.jsp';
//    			}
//    		else
//    			{
//    			$('#name').html('<h6>'+data.value+'<h6>');
//    			}
//            	
//            },
//            dataType : 'json'
//        });
//    });
//});



//$('#logSub').click(function(){
//	
//	$.get('http://localhost:8080/ControllerServlet', {email: $('#logEmail').val(),pass: $('#logPass').val()}, 
//			function (data, textStatus, jqXHR) {
//		
//		if(data.value == 'true')
//			{
//				window.location.href = '/Dashboard';
//			}
//		else
//			{
//				$('#name').html('<h6>'+data.value+'</h6>');
//			}
//		
//	},'json');
//});





$(function () {
    $('#logSub').on('click', function () {
    	
        $.ajax({
        	type: 'GET',
            url: 'http://localhost:8080/ControllerServlet',
				data:{
					email: $('#logEmail').val(),
					pass: $('#logPass').val()
				},
            success: function (data, status, xhr) {
            	
            	if(data.value == 'true')
    			{
            		window.location.href = '/Dashboard';
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