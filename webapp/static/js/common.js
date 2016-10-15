function callAjax(ajaxObj) {
	$.ajax(ajaxObj).fail(function(jqXHR) {
		if (jqXHR.status == 1500) {
			var error = JSON.parse(jqXHR.responseText);
			
			alert(error.errorMsg);
		}
		else {
			alert("사용자가 폭주하여 잠시 후 사용해주세요.");
		}
	});
}
