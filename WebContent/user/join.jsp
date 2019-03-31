<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 하기</title>
</head>
<body>
<form method="post" action="/users">
	<table border="1">
		<tr>
			<th colspan="2">회원 가입 신청서</th>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="ui_name" id="ui_name"></td>
		</tr>
		<tr>
			<th>아이디</th>
			<td><input type="text" name="ui_id" id="ui_id"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="ui_pwd" id="ui_pwd"></td>
		</tr>
		<tr>
			<th>메일주소</th>
			<td><input type="email" name="ui_email" id="ui_email"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><button>가입하기</button></td>
		</tr>
	</table>
	<input type="hidden" name="cmd" value="insert">
</form>
</body>
</html>