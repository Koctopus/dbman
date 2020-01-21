function upload(){
	var form = $('#register_form').get(0);
　　　　//FormData オブジェクトを作成
　　　　var formData = new FormData( form );

　　　　//Ajax実行
　　　　$.ajax({
　　　　　url : "/upload",　　　//実行するサーブレット
　　　　　dataType: "html",
　　　　　type : "post",
　　　　　data : formData,　　// dataに FormDataを指定
　　　　　processData: false, 　　 //Ajaxがdataを整形しない指定
　　　　　contentType: false　　　//contentTypeもfalseに指定
　　　　}).done(function(data) {        // Ajax通信が成功した時の処理
    	alert("アップロードが完了しました。");
		}
　　　　　//成功時の処理
　　　　}).fail(function(XMLHttpRequest, textStatus, errorThrown) { // Ajax通信が失敗した時の処理
    	alert("アップロードが失敗しました。");
	}
}