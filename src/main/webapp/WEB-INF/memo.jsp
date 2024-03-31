<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.Memo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メモアプリ</title>
</head>
<body>
	<h1>メモを入力してください</h1>
	<form action="./" method="post">
		<input type="text" name="memo">
		<input type="submit" value="保存">
	</form>
	<h2>過去のメモ</h2>
	<%
	var memoList = (ArrayList<com.example.Memo>) session.getAttribute("memoList");
	for (var memo : memoList) {
	%>
	<p><%= memo.getText() %></p>
	<%
	}
	%>
</body>
</html>
