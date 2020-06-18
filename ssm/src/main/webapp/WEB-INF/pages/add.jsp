<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2020/6/18
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/account/add" method="get">
    <input hidden name="cardNo">
   <span>姓名</span> <input name="name"> <br/>
    <span>金额</span><input name="money">
    <input type="submit" value="提交">
</form>

</body>
</html>
