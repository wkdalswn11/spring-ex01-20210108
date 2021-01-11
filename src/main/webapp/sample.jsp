<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>smaple 연습</h1>
<hr>
<h3>/sample/ ,get </h3>
<form action="/sample/" method="get">
 <input type="text"> <br>	
 <input type="submit" value="전송">
</form>
<h3>/sample/ ,post</h3>
<form action="/sample/" method="post">
 <input type="text"> <br>	
 <input type="submit" value="전송">
</form>
<h3>/sample/basic ,get</h3>
<form action="/sample/basic" method="get">
 <input type="text"> <br>	
 <input type="submit" value="전송">
</form>
<h3>/sample/basic , post</h3>
<form action="/sample/basic" method="post">
 <input type="text"> <br>	
 <input type="submit" value="전송">
</form>
<h3>/sample/basicOnlyGet , get</h3>
<form action="/sample/basicOnlyGet" method="get">
 <input type="text"> <br>	
 <input type="submit" value="전송">
</form>
<h3>/sample/basicOnlyPost, post</h3>
<form action="/sample/basicOnlyPost" method="post">
 <input type="text"> <br>	
 <input type="submit" value="전송">
</form>
</body>
</html>