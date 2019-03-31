<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 하기</title>
</head>
<body>
<script>
	if(msg!=null) {
		alert('${msg}');
	}
</script>
<form method="post" action="/users">
	<table border="1">
		<tr>
			<th>아이디</th>
			<td><input type="text" name="ui_id" id="ui_id"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="ui_pwd" id="ui_pwd"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><button>로그인</button></td>
		</tr>
	</table>
<input type="hidden" name="cmd" value="login">
</form>
<a href="/user/join.jsp">회원가입 하기</a>
</body>
</html>