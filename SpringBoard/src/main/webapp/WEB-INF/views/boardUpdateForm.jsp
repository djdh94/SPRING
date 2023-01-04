<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/boardUpdate" method="post">
		<input type="hidden" name="bno" value="${board.bno }"/>
		제목 : <input type="text" name="title" value="${board.title }"/>
		글쓴이 : <input type="text" name="writer" value="${board.writer }" readonly/><br/>
		본문 : <textarea rows="20" cols="100" name="content">${board.content }</textarea><br/>
		<input type="submit"/>
	</form>
</body>
</html>