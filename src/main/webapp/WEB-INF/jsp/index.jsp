<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

Login
<form action="${pageContext.request.contextPath}/userlogin" method="post">
  <p>username:<input type="text" name="username"/></p>
  <p>password:<input type="text" name="password"/></p>
  <input type="submit" value="submit"/> 
</form>
   
 <a href="${pageContext.request.contextPath}/api/properties">get all properties</a>
</body>
</html>