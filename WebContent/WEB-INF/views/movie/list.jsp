<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 리스트</title>
</head>
<body>
영화 리스트
<br>
<c:forEach items="${movieList}" var="row">
	번호 : ${row.miNum}, 
	제목 : ${row.miName}, 
	개봉년도 : ${row.miYear}, 
	국가 : ${row.miNational},
	제작사 : ${row.miVendor}, 
	감독 : ${row.miDirector}<br>
</c:forEach>
</body>
</html>