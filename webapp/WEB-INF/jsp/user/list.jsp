<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사용자 목록</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<style type="text/css">
.signup-form {
	padding: 30px;
	width: 400px;
}

#btnSignup {
	width: 100%;
}
</style>
</head>
<body>

	<div>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>사용자 ID</th>
					<th>이메일</th>
					<th>전화번호</th>
				</tr>
			</thead>
			<tbody>
			
			</tbody>
		</table>
	</div>
	<div class="signup-form">
		<div class="form-group">
			<label for="userEmail">Email</label>
		    <input type="email" class="form-control" id="userEmail" placeholder="Email">
		</div>
		<div class="form-group">
		    <label for="userPassword">Password</label>
		    <input type="password" class="form-control" id="userPassword" placeholder="Password">
		</div>
		<div class="form-group">
		    <label for="userPhoto">File input</label>
		    <input type="file" id="userPhoto">
  		</div>
  		<button id="btnSignup" class="btn btn-success">회원가입</button>
  </div>

<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="/static/js/common.js"></script>
<script type="text/javascript">
$(function() {
	
	function addUser(userId, userEmail, userTel) {
		var rowHTML = "<tr>";
		rowHTML += "<td>" + userId + "</td>";
		rowHTML += "<td>" + userEmail + "</td>";
		rowHTML += "<td>" + userTel + "</td>";
		rowHTML += "</tr>";
		
		$(".table tbody").append(rowHTML);
	}
	
	callAjax({
		url: "/api/user/list",
		success: function(users) {
			for (var i=0;i<users.length;i++) {
				var user = users[i];
				
				addUser(user.userId, user.userEmail, user.userTel);
			}
		} 
	});
	
	$("#btnSignup").on("click", function() {
		var userEmail = $("#userEmail").val();
		var userPassword = $("#userPassword").val();
		
		if (userEmail.trim() == "") {
			alert("이메일을 입력하세요.");
			$("#userEmail").focus();
			return;
		}
		else if (userPassword.trim() == "") {
			alert("비밀번호를 입력하세요.");
			$("#userPassword").focus();
			return;
		}
		
		var userData = {
			userEmail: userEmail,
			userPassword: userPassword
		};
		
		callAjax({
			url: "/api/user/add",
			method: "POST",
			contentType: "application/json;charset=utf-8",
			dataType: "json",
			data: JSON.stringify(userData),
			success: function (user) {
				console.log(user);
			}
		});
	});
});
</script>
</body>
</html>