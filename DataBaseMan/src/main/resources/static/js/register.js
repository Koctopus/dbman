function uploadfunction(){
	
	var formData = new FormData($('#register_form').get(0));
    
    $.ajax({
        url: '/upload',
        method:'POST',
        data:formData,
        processData:false,
        contentType:false,
        cache: false
    })
    .done(function(data, textStatus, jqXHR) {
        window.alert('結果：成功');
    })
    .fail(function(jqXHR, textStatus, errorThrown) {
        window.alert('結果：失敗');
        console.log(XMLHttpRequest.status);
        console.log(textStatus);
        console.log(errorThrown);
    }
}